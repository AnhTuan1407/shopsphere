package com.tuanha.order.mapper;

import com.tuanha.order.dto.request.OrderInfoCreationRequest;
import com.tuanha.order.dto.request.OrderInfoUpdationRequest;
import com.tuanha.order.dto.response.OrderInfoResponse;
import com.tuanha.order.entity.OrderInfo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class OrderInfoMapperImpl implements OrderInfoMapper {

    @Override
    public OrderInfo toOrderInfo(OrderInfoCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        OrderInfo.OrderInfoBuilder orderInfo = OrderInfo.builder();

        orderInfo.profileId( request.getProfileId() );
        orderInfo.fullName( request.getFullName() );
        orderInfo.phoneNumber( request.getPhoneNumber() );
        orderInfo.city( request.getCity() );
        orderInfo.district( request.getDistrict() );
        orderInfo.ward( request.getWard() );
        orderInfo.detailAddress( request.getDetailAddress() );
        orderInfo.defaultAddress( request.isDefaultAddress() );

        return orderInfo.build();
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
    public void toUpdateOrderInfo(OrderInfo orderInfo, OrderInfoUpdationRequest request) {
        if ( request == null ) {
            return;
        }

        orderInfo.setFullName( request.getFullName() );
        orderInfo.setPhoneNumber( request.getPhoneNumber() );
        orderInfo.setCity( request.getCity() );
        orderInfo.setDistrict( request.getDistrict() );
        orderInfo.setWard( request.getWard() );
        orderInfo.setDetailAddress( request.getDetailAddress() );
        orderInfo.setDefaultAddress( request.isDefaultAddress() );
    }
}
