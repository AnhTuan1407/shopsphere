package com.tuanha.order.controller;

import com.tuanha.order.dto.request.CartCreationRequest;
import com.tuanha.order.dto.request.CartItemCreationRequest;
import com.tuanha.order.dto.request.CartItemUpdationRequest;
import com.tuanha.order.dto.response.ApiResponse;
import com.tuanha.order.dto.response.CartItemResponse;
import com.tuanha.order.dto.response.CartResponse;
import com.tuanha.order.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {
    CartService cartService;

    @GetMapping("/{profileId}")
    ApiResponse<CartResponse> getCart(@PathVariable String profileId) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.getCartByProfileId(profileId))
                .build();
    }

    @PostMapping("/addToCart")
    ApiResponse<CartItemResponse> addToCart(@RequestBody CartItemCreationRequest request) {
        return ApiResponse.<CartItemResponse>builder()
                .result(cartService.addProductToCart(request))
                .build();
    }

    @GetMapping("/select/{cartItemId}")
    public ApiResponse<CartResponse> selectCartItem(@PathVariable Long cartItemId) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.selectCartItem(cartItemId))
                .build();
    }

    @PutMapping("/update-quantity/{cartItemId}")
    public ApiResponse<CartResponse> updateCart(@PathVariable Long cartItemId, @RequestBody CartItemUpdationRequest request) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.updateCartItem(cartItemId, request))
                .build();
    }

    @DeleteMapping("/{cartItemId}")
    public ApiResponse<Boolean> deleteCartItem(@PathVariable Long cartItemId) {
        return ApiResponse.<Boolean>builder()
                .result(cartService.deleteCartItem(cartItemId))
                .build();
    }

}
