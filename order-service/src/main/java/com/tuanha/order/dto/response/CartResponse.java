package com.tuanha.order.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    Long id;
    String profileId;
    double totalPrice;
    List<CartItemResponse> cartItems;
}
