package com.tuanha.order.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemCreationRequest {
    Long productId;
    Long productVariantId;
    Long supplierId;
    int quantity;
    double price;
    String profileId;
}
