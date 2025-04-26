package com.tuanha.product.dto.request;

import com.tuanha.product.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantCreationRequest {
    String color;
    String size;
    String imageUrl;
    String version;
    double price;
    int availableQuantity;
    int rating;
    long quantitySold;
    Long productId;
}
