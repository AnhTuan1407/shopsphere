package com.tuanha.product.service;

import com.tuanha.product.dto.request.ProductPurchaseRequest;
import com.tuanha.product.dto.request.ProductVariantCreationRequest;
import com.tuanha.product.dto.request.ProductVariantUpdationRequest;
import com.tuanha.product.dto.response.ProductPurchaseResponse;
import com.tuanha.product.dto.response.ProductVariantResponse;
import com.tuanha.product.entity.Product;
import com.tuanha.product.entity.ProductVariant;
import com.tuanha.product.exception.AppException;
import com.tuanha.product.exception.ErrorCode;
import com.tuanha.product.mapper.ProductVariantMapper;
import com.tuanha.product.repository.ProductRepository;
import com.tuanha.product.repository.ProductVariantRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
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

    @Transactional(rollbackFor = AppException.class)
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::getProductVariantId)
                .toList();
        var storedProduct = productVariantRepository.findAllById(productIds);
        if (storedProduct.size() != productIds.size()) {
            throw new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND);
        }

        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductVariantId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < sortedRequest.size(); i++) {
            var product = storedProduct.get(i);
            var productRequest = sortedRequest.get(i);

            if (product.getAvailableQuantity() < productRequest.getQuantity()) {
                throw new AppException(ErrorCode.INSUFFICIENT_STOCK);
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            product.setQuantitySold(product.getQuantitySold() + productRequest.getQuantity());
            productVariantRepository.save(product);

            var productName = productRepository.findById(product.getProduct().getId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)).getName();

            var productResponse = productVariantMapper.toProductPurchaseResponse(product);
            productResponse.setName(productName);
            productResponse.setQuantity(productRequest.getQuantity());

            purchasedProducts.add(productResponse);
        }

        return purchasedProducts;
    }

    public List<ProductVariantResponse> findProductVariantByProductId(Long id) {
        return productVariantRepository.findAllByProductId(id).stream()
                .map(productVariantMapper::toProductVariantResponse).toList();
    }
}
