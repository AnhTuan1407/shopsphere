package com.tuanha.payment.dto.request;

import com.tuanha.payment.dto.response.ProfileResponse;
import com.tuanha.payment.enums.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {
    double amount;
    PaymentMethod paymentMethod;
    Long orderId;
    ProfileResponse profile;
}
