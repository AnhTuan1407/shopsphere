package com.tuanha.product.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierResponse {
    Long id;
    String name;
    int follower;
    LocalDate createdAt;
    String address;
    String contactEmail;
    String phoneNumber;
    int rating;
}
