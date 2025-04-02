package com.tuanha.product.controller;

import com.tuanha.product.dto.request.ProductCreationRequest;
import com.tuanha.product.dto.request.ProductUpdationRequest;
import com.tuanha.product.dto.response.ApiResponse;
import com.tuanha.product.dto.response.ProductResponse;
import com.tuanha.product.service.ProductService;
import jakarta.validation.Valid;
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
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @GetMapping
    ApiResponse<List<ProductResponse>> getAllProducts() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProducts())
                .build();
    }

    @PostMapping
    ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreationRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.createProduct(request))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ProductResponse> updateProduct(@RequestBody @Valid ProductUpdationRequest request, @PathVariable("id") Long id) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteProduct(@PathVariable("id") Long id) {
        return ApiResponse.<String>builder()
                .result(productService.deleteProduct(id))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ProductResponse> getProductById(@PathVariable("id") Long id) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.findProductById(id))
                .build();
    }

    @GetMapping("/search")
    ApiResponse<List<ProductResponse>> searchProductByName(@RequestParam("name") String name) {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProductContainingName(name))
                .build();
    }

    @GetMapping("by-supplier")
    ApiResponse<List<ProductResponse>> getAllProductBySupplierId(@RequestParam("id") Long supplierId) {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProductBySupplierId(supplierId))
                .build();
    }

    @GetMapping("by-category")
    ApiResponse<List<ProductResponse>> getAllProductByCategoryId(@RequestParam("id") Long categoryId) {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProductByCategoryId(categoryId))
                .build();
    }

    @GetMapping("by-variant/{id}")
    ApiResponse<ProductResponse> getProductByVariantId(@PathVariable("id") Long productVariantId) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProductByVariantId(productVariantId))
                .build();
    }
}
