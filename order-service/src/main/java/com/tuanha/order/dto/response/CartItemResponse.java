package com.tuanha.order.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    Long id;
    Long productId;
    Long productVariantId;
    Long supplierId;
    int quantity;
    double price;
    boolean selected;
}
