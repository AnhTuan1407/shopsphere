package com.tuanha.order.dto.request;

import com.tuanha.order.entity.StatusOrder;
import com.tuanha.order.enums.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderUpdationRequest {
    LocalDate orderDate;
    @Enumerated(EnumType.STRING)
    StatusOrder statusOrder;
    Double totalPrice;
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
    Long orderInfoId;
    List<ProductPurchaseRequest> products;
}
