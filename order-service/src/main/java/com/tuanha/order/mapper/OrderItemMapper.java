package com.tuanha.order.mapper;

import com.tuanha.order.dto.request.OrderItemCreationRequest;
import com.tuanha.order.dto.response.OrderItemResponse;
import com.tuanha.order.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem toOrderItem(OrderItemCreationRequest request);

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}
