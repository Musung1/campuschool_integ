package com.example.campuschool_backend.dto.lecture;

import com.example.campuschool_backend.domain.lecture.Review;
import com.example.campuschool_backend.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewForm {
    private String content;
    private int rating;
    public Review toReview(UserEntity user) {
        return Review.of(content,rating,user);
    }
}
