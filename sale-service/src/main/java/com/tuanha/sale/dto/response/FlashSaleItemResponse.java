package com.tuanha.sale.dto.response;

import com.tuanha.sale.enums.FlashSaleItemStatus;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashSaleItemResponse {
    Long id;
    Long productId;
    Long flashSaleId;
    BigDecimal originalPrice;
    BigDecimal flashSalePrice;
    int discountPercentage;
    BigDecimal discountAmount;
    int totalQuantity;
    int soldQuantity;
    int maxPerUser;
    FlashSaleItemStatus status;
}
