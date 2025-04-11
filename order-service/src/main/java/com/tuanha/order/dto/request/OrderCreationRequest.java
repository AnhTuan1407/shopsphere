package com.tuanha.order.dto.request;

import com.tuanha.order.entity.StatusOrder;
import com.tuanha.order.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreationRequest {
    String profileId;
    @Builder.Default
    LocalDate orderDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    StatusOrder statusOrder = StatusOrder.PENDING;

    @Builder.Default
    Double totalPrice = 0.0;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    PaymentMethod paymentMethod = PaymentMethod.VISA;

    Long orderInfoId;
    List<ProductPurchaseRequest> products;
}
