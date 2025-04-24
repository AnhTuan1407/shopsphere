package com.tuanha.sale.dto.request;

import com.tuanha.sale.enums.FlashSaleItemStatus;
import com.tuanha.sale.enums.FlashSaleStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashSaleUpdationRequest {
    String name;
    String description;
    LocalDateTime startTime;
    LocalDateTime endTime;
    FlashSaleStatus status;
    LocalDateTime updatedAt;
    List<FlashSaleItemCreationRequest> flashSaleItems;
}
