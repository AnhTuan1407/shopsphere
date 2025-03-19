package com.tuanha.order.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderInfoResponse {
    Long id;
    String profileId;
    String fullName;
    String phoneNumber;
    String city;
    String district;
    String ward;
    String detailAddress;
    boolean defaultAddress;
}
