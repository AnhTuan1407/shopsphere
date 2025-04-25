package com.tuanha.sale.repository.httpClient;

import com.tuanha.sale.dto.response.ApiResponse;
import com.tuanha.sale.dto.response.ProductResponse;
import com.tuanha.sale.dto.response.ProductVariantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "${app.services.product}")
public interface ProductClient {
    @GetMapping(value = "products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<ProductResponse> getProductById(@PathVariable("id") Long id);
}
