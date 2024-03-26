package com.example.campuschool_backend.domain.lecture;

import com.example.campuschool_backend.domain.auditing.AuditingField;
import com.example.campuschool_backend.domain.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Review extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int rating;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    protected Review(){}
    public static Review of(String content, int rating, UserEntity user) {
        Review review = new Review();
        review.setContent(content);
        review.setRating(rating);
        review.setUser(user);
        return review;
    }
}
