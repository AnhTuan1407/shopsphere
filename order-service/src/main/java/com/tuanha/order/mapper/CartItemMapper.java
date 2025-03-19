package com.tuanha.order.mapper;

import com.tuanha.order.dto.request.CartItemCreationRequest;
import com.tuanha.order.dto.response.CartItemResponse;
import com.tuanha.order.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toCartItem(CartItemCreationRequest request);
    CartItemResponse toCartItemResponse(CartItem cartItem);
    void toUpdateCartItem(@MappingTarget CartItem cartItem, CartItemCreationRequest request);
}
