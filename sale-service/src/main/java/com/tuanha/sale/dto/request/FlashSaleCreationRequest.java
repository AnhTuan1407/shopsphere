package com.tuanha.sale.dto.request;

import com.tuanha.sale.enums.FlashSaleStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashSaleCreationRequest {
    String name;
    String description;
    LocalDateTime startTime;
    LocalDateTime endTime;
    FlashSaleStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<FlashSaleItemCreationRequest> flashSaleItems;
}
