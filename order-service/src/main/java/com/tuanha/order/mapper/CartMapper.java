package com.tuanha.order.mapper;

import com.tuanha.order.dto.request.CartCreationRequest;
import com.tuanha.order.dto.response.CartResponse;
import com.tuanha.order.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartResponse toCartResponse(Cart cart);
    Cart toCart(CartCreationRequest request);
}
