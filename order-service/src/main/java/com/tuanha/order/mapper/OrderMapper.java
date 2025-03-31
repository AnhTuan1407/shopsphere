package com.tuanha.order.mapper;

import com.tuanha.order.dto.request.OrderCreationRequest;
import com.tuanha.order.dto.request.OrderItemCreationRequest;
import com.tuanha.order.dto.request.OrderUpdationRequest;
import com.tuanha.order.dto.response.OrderInfoResponse;
import com.tuanha.order.dto.response.OrderResponse;
import com.tuanha.order.entity.Order;
import com.tuanha.order.entity.OrderInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderCreationRequest request);

    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "orderInfo", source = "orderInfo")
    OrderResponse toOrderResponse(Order order);

    OrderInfoResponse toOrderInfoResponse(OrderInfo orderInfo);

    void toUpdateOrder(@MappingTarget Order order, OrderUpdationRequest request);
}
