package com.tuanha.review.dto.response;

import com.tuanha.review.entity.ReviewImage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponse {
    Long id;
    ProductResponse product;
    ProductVariantResponse productVariant;
    ProfileResponse profile;
    int rating;
    String quality;
    String trueToDescription;
    String comment;
    LocalDateTime createdAt;
    @Builder.Default
    int likeCount = 0;
    String supplierReply;
    List<ReviewImageResponse> images;
}
