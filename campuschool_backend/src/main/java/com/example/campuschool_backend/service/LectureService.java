package com.example.campuschool_backend.service;

import com.example.campuschool_backend.domain.lecture.Review;
import com.example.campuschool_backend.domain.user.UserEntity;
import com.example.campuschool_backend.dto.lecture.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LectureService {
    Long createLecture(UserEntity userEntity, CreateLectureForm lectureForm);
    List<LectureCardDTO> popularLectures();
    List<LectureCardDTO> recentLectures();
    Page<LectureCardDTO> Lectures(LectureSearchParam lectureSearchParam, Pageable pageable);
    List<LectureCardDTO> getMyOpenLectures(UserEntity user);
    LectureDetailDTO getLectureDetail(Long id);
    Long registerLecture(UserEntity userEntity, Long id);
    Page<NotificationDTO> getNotifications(Long id,Pageable pageable);
    NotificationDTO postNotifications(Long id,CreateNotificationForm createNotificationForm);
    List<RegisterDTO> getRegisters(Long id);
    Boolean approveRegister(Long id,Long registerId);
    List<LectureCardDTO> getMyRegisterLectures(UserEntity user);
    List<LectureCardDTO> getMyWaitLectures(UserEntity user);
    void addView(Long id);
    Page<ReviewDTO> getReviews(Long id, Pageable pageable);
    ReviewDTO postReview(Long id, Review review);
    List<ReviewCardDTO> getRecentReviews();
}
