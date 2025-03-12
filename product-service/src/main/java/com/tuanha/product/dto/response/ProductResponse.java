package com.tuanha.product.dto.response;

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
    CategoryResponse category;
    List<ProductVariantResponse> variants;
    SupplierResponse supplier;
}
