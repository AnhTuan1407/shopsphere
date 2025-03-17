package com.tuanha.payment.dto.response;

import com.tuanha.payment.dto.request.ProfileRequest;
import com.tuanha.payment.enums.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    Long id;
    double amount;
    PaymentMethod paymentMethod;
    Long orderId;
    ProfileRequest profile;
}
