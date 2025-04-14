package com.tuanha.review.mapper;

import com.tuanha.review.dto.request.ReviewCreationRequest;
import com.tuanha.review.dto.request.ReviewUpdationRequest;
import com.tuanha.review.dto.response.ReviewResponse;
import com.tuanha.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toReview(ReviewCreationRequest request);

    ReviewResponse toReviewResponse(Review review);

    void toUpdateReviewImage(@MappingTarget Review review, ReviewUpdationRequest request);
}
