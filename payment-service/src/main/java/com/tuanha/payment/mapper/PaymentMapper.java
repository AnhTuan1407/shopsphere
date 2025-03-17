package com.tuanha.payment.mapper;

import com.tuanha.payment.dto.request.PaymentRequest;
import com.tuanha.payment.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toPayment(PaymentRequest request);
}
