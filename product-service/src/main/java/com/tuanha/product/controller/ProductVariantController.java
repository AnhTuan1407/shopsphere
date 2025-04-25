package com.tuanha.product.controller;

import com.google.protobuf.Api;
import com.tuanha.product.dto.request.ProductPurchaseRequest;
import com.tuanha.product.dto.request.ProductVariantCreationRequest;
import com.tuanha.product.dto.request.ProductVariantUpdationRequest;
import com.tuanha.product.dto.response.ApiResponse;
import com.tuanha.product.dto.response.ProductPurchaseResponse;
import com.tuanha.product.dto.response.ProductVariantResponse;
import com.tuanha.product.service.ProductVariantService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/product-variants")
public class ProductVariantController {
    ProductVariantService productVariantService;

    @GetMapping
    ApiResponse<List<ProductVariantResponse>> getAllProductVariants() {
        return ApiResponse.<List<ProductVariantResponse>>builder()
                .result(productVariantService.getAllProductVariants())
                .build();
    }

    @PostMapping
    ApiResponse<ProductVariantResponse> createProductVariant(@RequestBody ProductVariantCreationRequest request) {
        return ApiResponse.<ProductVariantResponse>builder()
                .result(productVariantService.createProductVariant(request))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ProductVariantResponse> updateProductVariant(@RequestBody ProductVariantUpdationRequest request, @PathVariable("id") Long id) {
        return ApiResponse.<ProductVariantResponse>builder()
                .result(productVariantService.updateProductVariant(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteProductVariant(@PathVariable("id") Long id) {
        return ApiResponse.<String>builder()
                .result(productVariantService.deleteProductVariant(id))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ProductVariantResponse> getProductVariantById(@PathVariable("id") Long id) {
        return ApiResponse.<ProductVariantResponse>builder()
                .result(productVariantService.findProductVariantById(id))
                .build();
    }

    @PostMapping("/purchase")
    ApiResponse<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> request) {
        return ApiResponse.<List<ProductPurchaseResponse>>builder()
                .result(productVariantService.purchaseProducts(request))
                .build();
    }

    @GetMapping("/by-product/{id}")
    ApiResponse<List<ProductVariantResponse>> getVariantsByProductId(@PathVariable("id") Long id) {
        return ApiResponse.<List<ProductVariantResponse>>builder()
                .result(productVariantService.findProductVariantByProductId(id))
                .build();
    }
}
