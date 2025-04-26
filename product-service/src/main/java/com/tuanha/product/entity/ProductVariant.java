package com.tuanha.product.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String color;
    String size;
    String version;
    String imageUrl;
    double price;
    int availableQuantity;

    @Builder.Default
    int rating = 0;

    @Builder.Default
    long quantitySold = 0;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;
}
