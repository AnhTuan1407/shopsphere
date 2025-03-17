package com.tuanha.order.dto.request;

import com.tuanha.order.dto.response.ProfileResponse;
import com.tuanha.order.enums.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
