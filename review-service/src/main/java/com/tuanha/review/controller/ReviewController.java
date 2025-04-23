package com.tuanha.review.controller;

import com.tuanha.review.dto.request.LikeReviewRequest;
import com.tuanha.review.dto.request.ReviewCreationRequest;
import com.tuanha.review.dto.response.ApiResponse;
import com.tuanha.review.dto.response.ReviewResponse;
import com.tuanha.review.service.ReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
@RequestMapping("")
public class ReviewController {
    ReviewService reviewService;

    @PostMapping("/")
    ApiResponse<ReviewResponse> createReview(@RequestBody ReviewCreationRequest request) {
        return ApiResponse.<ReviewResponse>builder()
                .result(reviewService.createReview(request))
                .build();
    }

    @GetMapping("/by-product/{id}")
    ApiResponse<List<ReviewResponse>> getAllReviewsByProductId(@PathVariable("id") Long id) {
        return ApiResponse.<List<ReviewResponse>>builder()
                .result(reviewService.getReviewByProductId(id))
                .build();
    }

    @GetMapping("/filter/{rating}")
    ApiResponse<List<ReviewResponse>> filterReviewByRating(@PathVariable("rating") int rating) {
        return ApiResponse.<List<ReviewResponse>>builder()
                .result(reviewService.filterReviewByRating(rating))
                .build();
    }

    @PutMapping("/like/{id}")
    ApiResponse<Integer> likeReview(@PathVariable("id") Long id, @RequestBody LikeReviewRequest request) {
        return ApiResponse.<Integer>builder()
                .result(reviewService.likeReview(id, request))
                .build();
    }

    @GetMapping("/like-count/{id}")
    ApiResponse<Integer> getLikeCountReview(@PathVariable("id") Long id) {
        return ApiResponse.<Integer>builder()
                .result(reviewService.getLikeCountReview(id))
                .build();
    }

    @GetMapping("/isLike/{reviewId}")
    ApiResponse<Boolean> getLikeCountReview(@PathVariable("reviewId") Long reviewId, @Param("profileId") String profileId) {
        return ApiResponse.<Boolean>builder()
                .result(reviewService.isLike(reviewId, profileId))
                .build();
    }
}
