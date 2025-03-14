package com.tuanha.product.dto.response;

import com.tuanha.product.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    Long id;
    String name;
    String description;
    String imageUrl;
    CategoryResponseForProduct category;
    List<ProductVariantResponse> variants;
}
