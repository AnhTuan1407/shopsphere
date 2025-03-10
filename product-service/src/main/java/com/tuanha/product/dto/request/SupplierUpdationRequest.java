package com.tuanha.product.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierUpdationRequest {
    String name;
    String address;
    String contactEmail;
    String phoneNumber;
}