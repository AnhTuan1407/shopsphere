package com.tuanha.product.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantUpdationRequest {
    String color;
    String size;
    String imageUrl;
    double price;
    int stock;
    int rating;
    long quantitySold;
    Long productId;
}
