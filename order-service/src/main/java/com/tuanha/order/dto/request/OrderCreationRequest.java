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
    Double totalPrice;
    PaymentMethod paymentMethod;
    int shippingFee;
    Long voucherId;
    Long orderInfoId;
    String note;
    List<ProductPurchaseRequest> products;
}
