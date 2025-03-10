package com.tuanha.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductVariantResponse {
    Long id;
    String color;
    String size;
    String imageUrl;
    double price;
    int stock;
    int rating;
    long quantitySold;
    Long productId;
}
