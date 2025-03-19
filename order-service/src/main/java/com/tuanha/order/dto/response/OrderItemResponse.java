package com.tuanha.order.dto.response;

import com.tuanha.order.entity.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemResponse {
    Long id;
    Long productVariantId;
    int quantity;
    double pricePerUnit;
}
