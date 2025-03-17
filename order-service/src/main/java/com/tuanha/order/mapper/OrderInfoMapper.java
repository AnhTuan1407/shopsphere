package com.tuanha.order.mapper;

import com.tuanha.order.dto.request.OrderInfoCreationRequest;
import com.tuanha.order.dto.request.OrderInfoUpdationRequest;
import com.tuanha.order.dto.response.OrderInfoResponse;
import com.tuanha.order.entity.OrderInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderInfoMapper {
    OrderInfo toOrderInfo(OrderInfoCreationRequest request);

    OrderInfoResponse toOrderInfoResponse(OrderInfo orderInfo);

    void toUpdateOrderInfo(@MappingTarget OrderInfo orderInfo, OrderInfoUpdationRequest request);
}
