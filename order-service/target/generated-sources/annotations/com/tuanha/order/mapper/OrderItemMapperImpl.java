package com.tuanha.order.mapper;

import com.tuanha.order.dto.request.OrderItemCreationRequest;
import com.tuanha.order.dto.response.OrderItemResponse;
import com.tuanha.order.entity.OrderItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItem toOrderItem(OrderItemCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.productVariantId( request.getProductVariantId() );
        orderItem.supplierId( request.getSupplierId() );
        orderItem.quantity( request.getQuantity() );
        orderItem.pricePerUnit( request.getPricePerUnit() );

        return orderItem.build();
    }

    @Override
    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemResponse.OrderItemResponseBuilder orderItemResponse = OrderItemResponse.builder();

        orderItemResponse.id( orderItem.getId() );
        orderItemResponse.productVariantId( orderItem.getProductVariantId() );
        orderItemResponse.supplierId( orderItem.getSupplierId() );
        orderItemResponse.quantity( orderItem.getQuantity() );
        orderItemResponse.pricePerUnit( orderItem.getPricePerUnit() );

        return orderItemResponse.build();
    }
}
