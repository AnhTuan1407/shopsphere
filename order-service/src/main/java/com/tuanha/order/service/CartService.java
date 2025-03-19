package com.tuanha.order.service;

import com.tuanha.order.dto.request.CartCreationRequest;
import com.tuanha.order.dto.request.CartItemCreationRequest;
import com.tuanha.order.dto.response.CartItemResponse;
import com.tuanha.order.dto.response.CartResponse;
import com.tuanha.order.entity.Cart;
import com.tuanha.order.entity.CartItem;
import com.tuanha.order.mapper.CartItemMapper;
import com.tuanha.order.mapper.CartMapper;
import com.tuanha.order.repository.CartItemRepository;
import com.tuanha.order.repository.CartRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartService {
    CartRepository cartRepository;

    CartItemRepository cartItemRepository;

    CartItemMapper cartItemMapper;

    CartMapper cartMapper;

    public CartResponse createCart(CartCreationRequest request) {
        Cart cart = cartMapper.toCart(request);
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }

    public CartResponse getCartByProfileId(String profileId) {
        var cart = cartRepository.findByProfileId(profileId);
        if (ObjectUtils.isEmpty(cart)) {
            Cart newCart = Cart.builder()
                    .profileId(profileId)
                    .cartItems(new ArrayList<>())
                    .build();
            cartRepository.save(newCart);
            return cartMapper.toCartResponse(newCart);
        }
        return cartMapper.toCartResponse(cart);
    }

    public CartItemResponse addProductToCart(CartItemCreationRequest request) {

        if (cartItemRepository.existsByProductVariantId(request.getProductVariantId())) {
            CartItem cartItem = cartItemRepository.findByProductVariantId(request.getProductVariantId());

            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItem.setPrice(cartItem.getPrice() + request.getPrice());

            return cartItemMapper.toCartItemResponse(cartItemRepository.save(cartItem));
        } else {
            CartItem cartItem = cartItemMapper.toCartItem(request);
            Cart cart = cartRepository.findByProfileId(request.getProfileId());
            cartItem.setCart(cart);

            return cartItemMapper.toCartItemResponse(cartItemRepository.save(cartItem));
        }
    }

    public CartResponse selectCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Cart item not found."));
        Cart cart = cartRepository.findByCartItemId(cartItemId);
        if (cartItem.isSelected()) {
            cartItem.setSelected(false);
            cart.setTotalPrice(cart.getTotalPrice() - cartItem.getPrice());
        } else {
            cartItem.setSelected(true);
            cart.setTotalPrice(cart.getTotalPrice() + cartItem.getPrice());
        }
        cartItemRepository.save(cartItem);
        return cartMapper.toCartResponse(cart);
    }
}
