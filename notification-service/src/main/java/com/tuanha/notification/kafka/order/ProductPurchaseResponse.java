package com.tuanha.notification.kafka.order;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductPurchaseResponse {
    Long productVariantId;
    String name;
    String size;
    String color;
    int quantity;
    double price;
}
