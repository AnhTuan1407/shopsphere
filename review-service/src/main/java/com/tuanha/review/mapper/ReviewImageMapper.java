package com.tuanha.review.mapper;

import com.tuanha.review.dto.request.ReviewImageCreationRequest;
import com.tuanha.review.dto.request.ReviewImageUpdationRequest;
import com.tuanha.review.dto.response.ReviewImageResponse;
import com.tuanha.review.entity.ReviewImage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewImageMapper {
    ReviewImage toReviewImage(ReviewImageCreationRequest request);

    ReviewImageResponse toReviewImageResponse(ReviewImage reviewImage);

    void toUpdateReviewImage(@MappingTarget ReviewImage reviewImage, ReviewImageUpdationRequest request);
}
