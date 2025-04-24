package com.tuanha.sale.dto.request;

import com.tuanha.sale.entity.FlashSale;
import com.tuanha.sale.enums.FlashSaleItemStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashSaleItemCreationRequest {
    Long productId;
    BigDecimal originalPrice;
    BigDecimal flashSalePrice;
    int discountPercentage;
    BigDecimal discountAmount;
    int totalQuantity;
    int soldQuantity;
    int maxPerUser;
    FlashSaleItemStatus status;
}
