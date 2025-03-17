package com.tuanha.order.kafka;

import com.tuanha.order.dto.response.ProductPurchaseResponse;
import com.tuanha.order.dto.response.ProfileResponse;
import com.tuanha.order.enums.PaymentMethod;
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
