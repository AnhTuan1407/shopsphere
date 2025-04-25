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
    Long supplierId;
    String description;
    LocalDateTime startTime;
    LocalDateTime endTime;
    List<FlashSaleItemCreationRequest> flashSaleItems;
}
