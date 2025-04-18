package com.tuanha.order.mapper;

import com.tuanha.order.dto.request.OrderCreationRequest;
import com.tuanha.order.dto.request.OrderUpdationRequest;
import com.tuanha.order.dto.response.OrderInfoResponse;
import com.tuanha.order.dto.response.OrderItemResponse;
import com.tuanha.order.dto.response.OrderResponse;
import com.tuanha.order.entity.Order;
import com.tuanha.order.entity.OrderInfo;
import com.tuanha.order.entity.OrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(OrderCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.profileId( request.getProfileId() );
        order.totalPrice( request.getTotalPrice() );
        order.paymentMethod( request.getPaymentMethod() );
        order.shippingFee( request.getShippingFee() );
        order.voucherId( request.getVoucherId() );
        order.note( request.getNote() );

        return order.build();
    }

    @Override
    public OrderResponse toOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.orderItems( orderItemListToOrderItemResponseList( order.getOrderItems() ) );
        orderResponse.orderInfo( toOrderInfoResponse( order.getOrderInfo() ) );
        orderResponse.id( order.getId() );
        orderResponse.profileId( order.getProfileId() );
        orderResponse.orderDate( order.getOrderDate() );
        orderResponse.statusOrder( order.getStatusOrder() );
        orderResponse.totalPrice( order.getTotalPrice() );
        orderResponse.paymentMethod( order.getPaymentMethod() );
        orderResponse.shippingFee( order.getShippingFee() );
        orderResponse.voucherId( order.getVoucherId() );
        orderResponse.note( order.getNote() );

        return orderResponse.build();
    }

    @Override
    public OrderInfoResponse toOrderInfoResponse(OrderInfo orderInfo) {
        if ( orderInfo == null ) {
            return null;
        }

        OrderInfoResponse.OrderInfoResponseBuilder orderInfoResponse = OrderInfoResponse.builder();

        orderInfoResponse.id( orderInfo.getId() );
        orderInfoResponse.profileId( orderInfo.getProfileId() );
        orderInfoResponse.fullName( orderInfo.getFullName() );
        orderInfoResponse.phoneNumber( orderInfo.getPhoneNumber() );
        orderInfoResponse.city( orderInfo.getCity() );
        orderInfoResponse.district( orderInfo.getDistrict() );
        orderInfoResponse.ward( orderInfo.getWard() );
        orderInfoResponse.detailAddress( orderInfo.getDetailAddress() );
        orderInfoResponse.defaultAddress( orderInfo.isDefaultAddress() );

        return orderInfoResponse.build();
    }

    @Override
    public void toUpdateOrder(Order order, OrderUpdationRequest request) {
        if ( request == null ) {
            return;
        }

        order.setOrderDate( request.getOrderDate() );
        order.setStatusOrder( request.getStatusOrder() );
        order.setTotalPrice( request.getTotalPrice() );
        order.setPaymentMethod( request.getPaymentMethod() );
    }

    protected OrderItemResponse orderItemToOrderItemResponse(OrderItem orderItem) {
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

    protected List<OrderItemResponse> orderItemListToOrderItemResponseList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemResponse> list1 = new ArrayList<OrderItemResponse>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemToOrderItemResponse( orderItem ) );
        }

        return list1;
    }
}
