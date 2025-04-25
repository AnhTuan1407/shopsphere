package com.tuanha.sale.entity;

import com.tuanha.sale.enums.DiscountType;
import com.tuanha.sale.enums.FlashSaleItemStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashSaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "flash_sale_id")
    FlashSale flashSale;

    Long productId;
    Long productVariantId;
    String productName;
    String variantType;
    String imageUrl;

    @Column(name = "original_price")
    BigDecimal originalPrice;

    @Column(name = "flash_sale_price")
    BigDecimal flashSalePrice;

    @Column(name = "discount_type")
    @Enumerated(EnumType.STRING)
    DiscountType discountType;

    @Column(name = "discount_value")
    BigDecimal discountValue;

    @Column(name = "total_quantity")
    int totalQuantity;

    @Column(name = "sold_quantity")
    @Builder.Default
    int soldQuantity = 0;

    @Column(name = "max_per_user")
    int maxPerUser;

    @Enumerated(EnumType.STRING)
    FlashSaleItemStatus status;
}
