package com.example.campuschool_backend.repository;

import com.example.campuschool_backend.domain.lecture.Lecture;
import com.example.campuschool_backend.domain.lecture.Notification;
import com.example.campuschool_backend.domain.lecture.Review;
import com.example.campuschool_backend.dto.lecture.LectureSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomLectureRepository {
    List<Lecture> findPopularLectures();
    List<Lecture> findRecentLectures();
    Page<Lecture> findLectures(LectureSearchParam lectureSearchParam, Pageable pageable);
    List<Lecture> findMyOpenLectures(Long id);
    Page<Notification> findNotification(Long id, Pageable pageable);
    List<Lecture> findMyRegisterLectures(Long id);
    List<Lecture> findMyWaitLectures(Long id);
    Page<Review> findReview(Long id, Pageable pageable);
    List<Review> findRecentReviews();
}
