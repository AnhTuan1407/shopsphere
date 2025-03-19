package com.tuanha.order.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderInfoUpdationRequest {
    String fullName;
    String phoneNumber;
    String city;
    String district;
    String ward;
    String detailAddress;
    boolean defaultAddress;
}
