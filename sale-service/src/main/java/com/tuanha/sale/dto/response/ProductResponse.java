package com.tuanha.sale.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponse {
    Long id;
    String name;
    String description;
    String imageUrl;
    List<ProductVariantResponse> variants;
}
