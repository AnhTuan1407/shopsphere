package com.tuanha.sale.dto.request;

import com.tuanha.sale.enums.DiscountType;
import com.tuanha.sale.enums.FlashSaleItemStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashSaleItemUpdationRequest {
    Long productId;
    BigDecimal originalPrice;
    BigDecimal flashSalePrice;
    DiscountType discountType;
    BigDecimal discountValue;
    int totalQuantity;
    int maxPerUser;
    FlashSaleItemStatus status;
}
