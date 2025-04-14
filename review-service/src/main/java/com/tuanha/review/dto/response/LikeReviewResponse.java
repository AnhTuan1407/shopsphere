package com.tuanha.review.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LikeReviewResponse {
    Long id;
    Long reviewId;
    String profileId;
}
