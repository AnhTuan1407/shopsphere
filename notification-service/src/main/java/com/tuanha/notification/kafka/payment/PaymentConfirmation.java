package com.tuanha.notification.kafka.payment;

import com.tuanha.notification.kafka.order.ProfileResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentConfirmation {
    double amount;
    PaymentMethod paymentMethod;
    Long orderId;
    String firstName;
    String lastName;
    String email;
}