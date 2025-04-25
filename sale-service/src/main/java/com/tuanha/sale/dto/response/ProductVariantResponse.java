package com.tuanha.sale.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductVariantResponse {
    Long id;
    String color;
    String size;
    String imageUrl;
    double price;
    int availableQuantity;
}
