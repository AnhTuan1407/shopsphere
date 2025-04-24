package com.tuanha.sale.dto.response;

import com.tuanha.sale.dto.request.FlashSaleItemCreationRequest;
import com.tuanha.sale.enums.FlashSaleStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashSaleResponse {
    Long id;
    String name;
    String description;
    LocalDateTime startTime;
    LocalDateTime endTime;
    FlashSaleStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<FlashSaleItemResponse> flashSaleItems;
}
