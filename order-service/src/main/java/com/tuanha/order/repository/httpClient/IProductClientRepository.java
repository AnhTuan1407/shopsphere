package com.tuanha.order.repository.httpClient;

import com.tuanha.order.dto.response.ApiResponse;
import com.tuanha.order.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "product-service",
        url = "${app.services.product}"
)
public interface IProductClientRepository {
    @GetMapping("/products/by-variant/{id}")
    ApiResponse<ProductResponse> getProductByVariantId(@PathVariable("id") Long id);
}
