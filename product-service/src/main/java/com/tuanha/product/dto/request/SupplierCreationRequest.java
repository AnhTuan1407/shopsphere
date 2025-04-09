package com.tuanha.product.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierCreationRequest {
    String name;
    String address;
    String contactEmail;
    String phoneNumber;
    String password;
}
