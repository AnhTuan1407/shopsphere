package com.tuanha.product.service;

import com.tuanha.product.dto.request.ProductCreationRequest;
import com.tuanha.product.dto.request.ProductUpdationRequest;
import com.tuanha.product.dto.response.ProductResponse;
import com.tuanha.product.entity.Category;
import com.tuanha.product.entity.Product;
import com.tuanha.product.entity.Supplier;
import com.tuanha.product.exception.AppException;
import com.tuanha.product.exception.ErrorCode;
import com.tuanha.product.mapper.ProductMapper;
import com.tuanha.product.repository.CategoryRepository;
import com.tuanha.product.repository.ProductRepository;
import com.tuanha.product.repository.SupplierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService {
    ProductRepository productRepository;

    CategoryRepository categoryRepository;

    SupplierRepository supplierRepository;

    ProductMapper productMapper;

    public ProductResponse createProduct(ProductCreationRequest request) {
        if (productRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.PRODUCT_EXISTS);

        Product product = productMapper.toProduct(request);
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        product.setCategory(category);

        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_EXISTS));
        product.setSupplier(supplier);

        return productMapper.toProductResponse(productRepository.save(product));
    }


    public List<ProductResponse> getAllProducts() {

        List<ProductResponse> productResponses = productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();

        if (CollectionUtils.isEmpty(productResponses))
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);

        return productResponses;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProduct(ProductUpdationRequest request, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.toUpdateProduct(product, request);

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(Long id) {
        productRepository.findById(id).ifPresentOrElse(
                productRepository::delete, () -> {
                    throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
                }
        );
        return "Product has been deleted";
    }

    public ProductResponse findProductById(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> getAllProductContainingName(String name) {

        List<ProductResponse> productResponses = productRepository.findByNameContainingIgnoreCase(name).stream()
                .map(productMapper::toProductResponse)
                .toList();

        return productResponses;
    }

    public List<ProductResponse> getAllProductBySupplierId(Long id) {
        List<ProductResponse> productResponses = productRepository.findBySupplierId(id).stream()
                .map(productMapper::toProductResponse)
                .toList();

        return productResponses;
    }

    public List<ProductResponse> getAllProductByCategoryId(Long id) {
        List<ProductResponse> productResponses = productRepository.findByCategoryId(id).stream()
                .map(productMapper::toProductResponse)
                .toList();

        return productResponses;
    }

    public ProductResponse getProductByVariantId(Long id) {
        Product productResponse = productRepository.findByProductVariantId(id);
        return  productMapper.toProductResponse(productResponse);
    }
}
