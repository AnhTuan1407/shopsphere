package com.tuanha.review.repository.httpClient;

import com.tuanha.review.dto.response.ApiResponse;
import com.tuanha.review.dto.response.ProductResponse;
import com.tuanha.review.dto.response.ProductVariantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "${app.services.product}")
public interface ProductClient {
    @GetMapping(value = "products/by-variant/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<ProductResponse> getProductByVariantId(@PathVariable("id") Long id);

    @GetMapping(value = "/product-variants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<ProductVariantResponse> getVariantById(@PathVariable("id") Long id);
}
