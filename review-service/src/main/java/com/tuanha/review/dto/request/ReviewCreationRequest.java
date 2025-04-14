package com.tuanha.review.dto.request;

import com.tuanha.review.entity.ReviewImage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ReviewCreationRequest {
    Long productId;
    Long productVariantId;
    String profileId;
    int rating;
    String quality;
    String trueToDescription;
    String comment;
    List<ReviewImageCreationRequest> imageUrls;
}
