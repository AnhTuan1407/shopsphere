package com.tuanha.order.controller;

import com.tuanha.order.dto.request.OrderCreationRequest;
import com.tuanha.order.dto.request.OrderInfoCreationRequest;
import com.tuanha.order.dto.request.OrderInfoUpdationRequest;
import com.tuanha.order.dto.request.UpdateStatusRequest;
import com.tuanha.order.dto.response.ApiResponse;
import com.tuanha.order.dto.response.OrderInfoResponse;
import com.tuanha.order.dto.response.OrderResponse;
import com.tuanha.order.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {

    OrderService orderService;

    @PostMapping("/")
    ApiResponse<OrderResponse> createOrder(@RequestBody OrderCreationRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.createOrder(request))
                .build();
    }

    @GetMapping("/")
    ApiResponse<List<OrderResponse>> getAllOrders() {
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getAllOrders())
                .build();
    }

    @GetMapping("/profile/{profileId}")
    ApiResponse<List<OrderResponse>> getAllOrdersByProfileId(@PathVariable("profileId") String profileId) {
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getAllOrdersByProfileId(profileId))
                .build();
    }

    @PutMapping("/status/{id}")
    ApiResponse<OrderResponse> updateStatusOrder(@PathVariable("id") Long id,
                                                 @RequestBody UpdateStatusRequest  request) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.updateStatusOrder(id, request))
                .build();
    }

    @PostMapping("/address-info")
    ApiResponse<OrderInfoResponse> addNewOrderInfo(@RequestBody OrderInfoCreationRequest request) {
        return ApiResponse.<OrderInfoResponse>builder()
                .result(orderService.addNewOrderInfo(request))
                .build();
    }

    @PutMapping("/address-info/{id}")
    ApiResponse<OrderInfoResponse> updateOrderInfo(@RequestBody OrderInfoUpdationRequest request,
                                                   @PathVariable("id") Long id) {
        return ApiResponse.<OrderInfoResponse>builder()
                .result(orderService.updateOrderInfo(request, id))
                .build();
    }

    @GetMapping("/address-info/{profileId}")
    ApiResponse<List<OrderInfoResponse>> getAllOrderInfo(@PathVariable("profileId") String profileId) {
        return ApiResponse.<List<OrderInfoResponse>>builder()
                .result(orderService.getAllOrderInfo(profileId))
                .build();
    }

    @PutMapping("/address-info/set-default/{id}")
    ApiResponse<OrderInfoResponse> setDefaultAddress(@PathVariable("id") Long id) {
        return ApiResponse.<OrderInfoResponse>builder()
                .result(orderService.setDefaultAddress(id))
                .build();
    }

    @GetMapping("/address-info/find-id/{id}")
    ApiResponse<OrderInfoResponse> findOrderInfoById(@PathVariable("id") Long id) {
        return ApiResponse.<OrderInfoResponse>builder()
                .result(orderService.findOrderInfoById(id))
                .build();
    }

    @DeleteMapping("/address-info/{id}")
    ApiResponse<Boolean> deleteOrderInfoById(@PathVariable("id") Long id) {
        return ApiResponse.<Boolean>builder()
                .result(orderService.deleteOrderInfoById(id))
                .build();
    }
}
