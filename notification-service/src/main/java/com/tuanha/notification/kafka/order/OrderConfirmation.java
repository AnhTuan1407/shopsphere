package com.tuanha.notification.kafka.order;

import com.tuanha.notification.kafka.payment.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderConfirmation {
    double totalPrice;
    PaymentMethod paymentMethod;
    ProfileResponse profile;
    ArrayList<ProductPurchaseResponse> products;
    Long orderId;
}