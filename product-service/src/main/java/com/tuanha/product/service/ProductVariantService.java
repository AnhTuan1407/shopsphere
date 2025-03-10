package com.tuanha.product.service;

import com.tuanha.product.dto.request.ProductCreationRequest;
import com.tuanha.product.dto.request.ProductUpdationRequest;
import com.tuanha.product.dto.request.ProductVariantCreationRequest;
import com.tuanha.product.dto.request.ProductVariantUpdationRequest;
import com.tuanha.product.dto.response.ProductResponse;
import com.tuanha.product.dto.response.ProductVariantResponse;
import com.tuanha.product.entity.Category;
import com.tuanha.product.entity.Product;
import com.tuanha.product.entity.ProductVariant;
import com.tuanha.product.entity.Supplier;
import com.tuanha.product.exception.AppException;
import com.tuanha.product.exception.ErrorCode;
import com.tuanha.product.mapper.ProductMapper;
import com.tuanha.product.mapper.ProductVariantMapper;
import com.tuanha.product.repository.CategoryRepository;
import com.tuanha.product.repository.ProductRepository;
import com.tuanha.product.repository.ProductVariantRepository;
import com.tuanha.product.repository.SupplierRepository;
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
public class ProductVariantService {

    ProductRepository productRepository;

    ProductVariantRepository productVariantRepository;

    ProductVariantMapper productVariantMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public ProductVariantResponse createProductVariant(ProductVariantCreationRequest request) {

        ProductVariant productVariant = productVariantMapper.toProductVariant(request);

        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productVariant.setProduct(product);

        return productVariantMapper.toProductVariantResponse(productVariantRepository.save(productVariant));
    }

    public List<ProductVariantResponse> getAllProductVariants() {

        List<ProductVariantResponse> productVariantResponses = productVariantRepository.findAll()
                .stream()
                .map(productVariantMapper::toProductVariantResponse)
                .toList();

        if (CollectionUtils.isEmpty(productVariantResponses))
            throw new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND);

        return productVariantResponses;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ProductVariantResponse updateProductVariant(ProductVariantUpdationRequest request, Long id) {
        ProductVariant productVariant = productVariantRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));
        productVariantMapper.toUpdateProductVariant(productVariant, request);

        return productVariantMapper.toProductVariantResponse(productVariantRepository.save(productVariant));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProductVariant(Long id) {
        productVariantRepository.findById(id).ifPresentOrElse(
                productVariantRepository::delete, () -> {
                    throw new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND);
                }
        );
        return "Product variant has been deleted";
    }

    public ProductVariantResponse findProductVariantById(Long id) {

        ProductVariant productVariant = productVariantRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));

        return productVariantMapper.toProductVariantResponse(productVariant);
    }
}
