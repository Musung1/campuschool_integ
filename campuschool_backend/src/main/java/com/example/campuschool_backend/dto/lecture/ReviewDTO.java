package com.example.campuschool_backend.dto.lecture;

import com.example.campuschool_backend.domain.lecture.Review;
import com.example.campuschool_backend.domain.user.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewDTO {
    private String userImg;
    private String name;
    private String content;
    private int rating;
    private LocalDateTime createdAt;
    private String createdBy;

    public static ReviewDTO from(Review review) {
        return ReviewDTO.builder()
                .userImg(review.getUser().getImg())
                .name(review.getUser().getName())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .createdBy(review.getUser().getName())
                .build();

    }
}
