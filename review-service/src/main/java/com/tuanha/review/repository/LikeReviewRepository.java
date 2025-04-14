package com.tuanha.review.repository;

import com.tuanha.review.entity.LikeReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeReviewRepository extends JpaRepository<LikeReview, Long> {
    Boolean existsByProfileId(String profileId);
    List<LikeReview> findAllByReviewId(Long reviewId);
    Optional<LikeReview> findByReviewIdAndProfileId(Long reviewId, String profileId);
    int countByReviewId(Long reviewId);

}
