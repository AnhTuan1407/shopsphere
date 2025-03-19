package com.tuanha.order.repository.httpClient;


import com.tuanha.order.dto.request.PaymentRequest;
import com.tuanha.order.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${app.services.payment}"
)
public interface PaymentClient {
    @PostMapping("/")
    ApiResponse<Long> requestOrderPayment(@RequestBody PaymentRequest request);
}
