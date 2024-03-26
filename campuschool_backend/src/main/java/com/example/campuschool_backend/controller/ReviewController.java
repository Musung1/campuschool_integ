package com.example.campuschool_backend.controller;

import com.example.campuschool_backend.domain.lecture.Review;
import com.example.campuschool_backend.domain.user.UserEntity;
import com.example.campuschool_backend.dto.lecture.ReviewCardDTO;
import com.example.campuschool_backend.dto.lecture.ReviewDTO;
import com.example.campuschool_backend.dto.lecture.ReviewForm;
import com.example.campuschool_backend.security.PrincipalUser;
import com.example.campuschool_backend.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/class")
@RestController
public class ReviewController {
    private final LectureService lectureService;
    @GetMapping("/{id}/review")
    public ResponseEntity<Page<ReviewDTO>> getReview(@PathVariable Long id,
                                                     Pageable pageable) {
        Page<ReviewDTO> reviews = lectureService.getReviews(id,pageable);
        System.out.println(pageable.getSort().toString());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.toString());
        return ResponseEntity.ok(reviews);
    }
    @PostMapping("/{id}/review")
    public ResponseEntity<ReviewDTO> postReview(@PathVariable Long id,
                                     @AuthenticationPrincipal UserDetails userDetails,
                                     @RequestBody ReviewForm reviewForm) {
        PrincipalUser principalUser = (PrincipalUser) userDetails;
        UserEntity user = principalUser.getUser();
        Review review = reviewForm.toReview(user);
        user.addReview(review);
        ReviewDTO reviewDTO = lectureService.postReview(id,review);
        return ResponseEntity.ok(reviewDTO);
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewCardDTO>> getRecentReview() {
        List<ReviewCardDTO> reviewCards = lectureService.getRecentReviews();
        return ResponseEntity.ok(reviewCards);
    }

}
