package com.tuanha.order.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierResponse {
    Long id;
    String name;
    int follower;
    String address;
    String contactEmail;
    String phoneNumber;
    int rating;
    LocalDate createdAt;
}
