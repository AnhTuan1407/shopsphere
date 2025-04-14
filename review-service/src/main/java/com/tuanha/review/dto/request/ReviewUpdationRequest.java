package com.tuanha.review.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ReviewUpdationRequest {
    int rating;
    String quality;
    String trueToDescription;
    String comment;
    String supplierReply;
}
