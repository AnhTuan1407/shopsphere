package com.tuanha.product.service;

import com.tuanha.product.dto.request.ProductCreationRequest;
import com.tuanha.product.dto.request.ProductUpdationRequest;
import com.tuanha.product.dto.response.CategoryResponse;
import com.tuanha.product.dto.response.ProductResponse;
import com.tuanha.product.entity.Category;
import com.tuanha.product.entity.Product;
import com.tuanha.product.exception.AppException;
import com.tuanha.product.exception.ErrorCode;
import com.tuanha.product.mapper.ProductMapper;
import com.tuanha.product.repository.CategoryRepository;
import com.tuanha.product.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/")
public class ProductService {
    ProductRepository productRepository;

    CategoryRepository categoryRepository;

    ProductMapper productMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse createProduct(ProductCreationRequest request) {
        if (productRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.PRODUCT_EXISTS);

        Product product = productMapper.toProduct(request);
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        product.setCategory(category);

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

    public ProductResponse findProductByName(String name) {

        Product product = productRepository.findByName(name);

        if (ObjectUtils.isEmpty(product))
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);

        ProductResponse productResponse = productMapper.toProductResponse(product);

        return productResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProduct(ProductUpdationRequest request, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.toUpdateProduct(product, request);

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productRepository.deleteById(id);
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
}
