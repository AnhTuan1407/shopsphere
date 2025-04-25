package com.tuanha.review.service;

import com.tuanha.review.dto.request.LikeReviewRequest;
import com.tuanha.review.dto.request.ReviewCreationRequest;
import com.tuanha.review.dto.request.ReviewImageCreationRequest;
import com.tuanha.review.dto.response.ReviewResponse;
import com.tuanha.review.entity.LikeReview;
import com.tuanha.review.entity.Review;
import com.tuanha.review.exception.AppException;
import com.tuanha.review.exception.ErrorCode;
import com.tuanha.review.mapper.LikeReviewMapper;
import com.tuanha.review.mapper.ReviewImageMapper;
import com.tuanha.review.mapper.ReviewMapper;
import com.tuanha.review.repository.LikeReviewRepository;
import com.tuanha.review.repository.ReviewImageRepository;
import com.tuanha.review.repository.ReviewRepository;
import com.tuanha.review.repository.httpClient.ProductClient;
import com.tuanha.review.repository.httpClient.ProfileClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ReviewService {
    ReviewMapper reviewMapper;
    ReviewImageMapper reviewImageMapper;
    ReviewRepository reviewRepository;
    LikeReviewRepository likeReviewRepository;
    ReviewImageRepository reviewImageRepository;
    ProfileClient profileClient;
    ProductClient productClient;
    LikeReviewMapper likeReviewMapper;

    public ReviewResponse createReview(ReviewCreationRequest request) {
        var profileResponse = profileClient.getProfileById(request.getProfileId());
        var productResponse = productClient.getProductByVariantId(request.getProductVariantId());
        var productVariantResponse = productClient.getVariantById(request.getProductVariantId());
        var review = reviewMapper.toReview(request);
        review = reviewRepository.save(review);

        for (ReviewImageCreationRequest img : request.getImageUrls()) {
            var reviewImage = reviewImageMapper.toReviewImage(img);
            reviewImage.setReview(review);
            reviewImageRepository.save(reviewImage);
        }
        var reviewResponse = reviewMapper.toReviewResponse(review);
        reviewResponse.setProfile(profileResponse.getResult());
        reviewResponse.setProduct(productResponse.getResult());
        reviewResponse.setProductVariant(productVariantResponse.getResult());
        return reviewResponse;
    }

    public List<ReviewResponse> getReviewByProductId(Long id) {
        var reviews = reviewRepository.findAllByProductId(id);
        var reviewsResponse = reviews.stream().map(reviewMapper::toReviewResponse).toList();
        for (int i = 0; i < reviews.size(); i++) {
            var profileResponse = profileClient.getProfileById(reviews.get(i).getProfileId());
            var productResponse = productClient.getProductByVariantId(reviews.get(i).getProductVariantId());
            var productVariantResponse = productClient.getVariantById(reviews.get(i).getProductVariantId());

            reviewsResponse.get(i).setProfile(profileResponse.getResult());
            reviewsResponse.get(i).setProduct(productResponse.getResult());
            reviewsResponse.get(i).setProductVariant(productVariantResponse.getResult());
        }
        return reviewsResponse;
    }

    public List<ReviewResponse> filterReviewByRating(int rating) {
        var reviews = reviewRepository.findAllByRating(rating);
        var reviewsResponse = reviews.stream().map(reviewMapper::toReviewResponse).toList();
        for (int i = 0; i < reviews.size(); i++) {
            var profileResponse = profileClient.getProfileById(reviews.get(i).getProfileId());
            var productResponse = productClient.getProductByVariantId(reviews.get(i).getProductVariantId());
            var productVariantResponse = productClient.getVariantById(reviews.get(i).getProductVariantId());

            reviewsResponse.get(i).setProfile(profileResponse.getResult());
            reviewsResponse.get(i).setProduct(productResponse.getResult());
            reviewsResponse.get(i).setProductVariant(productVariantResponse.getResult());
        }
        return reviewsResponse;
    }

    public int likeReview(Long reviewId, LikeReviewRequest request) {
        var review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));
        var existingLike = likeReviewRepository.findByReviewIdAndProfileId(reviewId, request.getProfileId());

        if (existingLike.isPresent()) {
            // Unlike
            likeReviewRepository.delete(existingLike.get());
        } else {
            // Like
            var like = new LikeReview(null, request.getProfileId(), review);
            likeReviewRepository.save(like);
        }

        return likeReviewRepository.countByReviewId(reviewId);
    }

    public int getLikeCountReview(Long id) {
        var likedReviews = likeReviewRepository.findAllByReviewId(id);
        return likedReviews.size();
    }

    public boolean isLike(Long reviewId, String profileId) {
        var review = reviewRepository.findById(reviewId).orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));
        var likedReviews = likeReviewRepository.findAllByReviewId(review.getId());
        if (!likedReviews.isEmpty()) {
            for (LikeReview likedReview : likedReviews) {
                if (Objects.equals(likedReview.getProfileId(), profileId)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Integer countReviewByProductId(Long id) {
        return reviewRepository.countByProductId(id);
    }

    public Double averageRatingByProductId(Long id) {
        return reviewRepository.findAverageRatingByProductId(id);
    }
}
