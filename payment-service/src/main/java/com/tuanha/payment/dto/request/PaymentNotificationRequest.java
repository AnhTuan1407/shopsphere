package com.tuanha.payment.dto.request;

import com.tuanha.payment.enums.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentNotificationRequest {
    double amount;
    Long orderId;
    PaymentMethod paymentMethod;
    String firstName;
    String lastName;
    String email;
}
