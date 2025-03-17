package com.tuanha.payment.controller;

import com.tuanha.payment.dto.request.PaymentRequest;
import com.tuanha.payment.dto.response.ApiResponse;
import com.tuanha.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {
    PaymentService paymentService;

    @PostMapping
    public ApiResponse<Long> createPayment(@RequestBody PaymentRequest request) {
        return ApiResponse.<Long>builder()
                .result(paymentService.createPayment(request))
                .build();
    }
}
