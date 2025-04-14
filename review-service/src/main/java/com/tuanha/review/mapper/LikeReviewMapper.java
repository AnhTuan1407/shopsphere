package com.tuanha.review.mapper;

import com.tuanha.review.dto.response.LikeReviewResponse;
import com.tuanha.review.entity.LikeReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeReviewMapper {
    LikeReviewResponse toLikeReviewResponse(LikeReview likeReview);
}
