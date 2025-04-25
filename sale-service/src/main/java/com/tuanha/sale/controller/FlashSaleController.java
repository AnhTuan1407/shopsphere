package com.tuanha.sale.controller;

import com.tuanha.sale.dto.request.FlashSaleCreationRequest;
import com.tuanha.sale.dto.response.ApiResponse;
import com.tuanha.sale.dto.response.FlashSaleResponse;
import com.tuanha.sale.service.FlashSaleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flash-sale")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlashSaleController {
    FlashSaleService flashSaleService;

    @PostMapping("")
    public ApiResponse<FlashSaleResponse> createFlashSale(@RequestBody FlashSaleCreationRequest request) {
        return ApiResponse.<FlashSaleResponse>builder()
                .result(flashSaleService.createFlashSale(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<FlashSaleResponse>> getAllFlashSales() {
        return ApiResponse.<List<FlashSaleResponse>>builder()
                .result(flashSaleService.getAllFlashSales())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<FlashSaleResponse> getFlashSaleById(@PathVariable Long id) {
        return ApiResponse.<FlashSaleResponse>builder()
                .result(flashSaleService.getFlashSaleById(id))
                .build();
    }

    @GetMapping("/active")
    public ApiResponse<List<FlashSaleResponse>> getActiveFlashSales() {
        return ApiResponse.<List<FlashSaleResponse>>builder()
                .result(flashSaleService.getActiveFlashSales())
                .build();
    }

    @GetMapping("/upcoming")
    public ApiResponse<List<FlashSaleResponse>> getUpcomingFlashSales() {
        return ApiResponse.<List<FlashSaleResponse>>builder()
                .result(flashSaleService.getUpcomingFlashSales())
                .build();
    }

    @GetMapping("/supplier/{id}")
    public ApiResponse<List<FlashSaleResponse>> getFlashSaleBySupplierId(@PathVariable("id") Long id) {
        return ApiResponse.<List<FlashSaleResponse>>builder()
                .result(flashSaleService.getFlashSaleBySupplierId(id))
                .build();
    }
}
