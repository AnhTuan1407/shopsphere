package com.tuanha.product.dto.response.response;

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
    int rating;
    private List<ProductResponse> products;
}
