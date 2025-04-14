package com.tuanha.review.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class LikeReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String profileId;

    @ManyToOne
    @JoinColumn(name = "review_id")
    Review review;
}
