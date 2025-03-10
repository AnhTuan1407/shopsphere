package com.tuanha.product.dto.response.request;

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
}
