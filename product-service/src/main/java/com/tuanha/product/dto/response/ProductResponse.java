package com.tuanha.product.dto.response;

import com.tuanha.product.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    double price;
    int stock;
    CategoryResponse category;
}
