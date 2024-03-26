package com.example.campuschool_backend.service.impl;

import com.example.campuschool_backend.domain.lecture.Lecture;
import com.example.campuschool_backend.domain.lecture.Notification;
import com.example.campuschool_backend.domain.lecture.Register;
import com.example.campuschool_backend.domain.lecture.Review;
import com.example.campuschool_backend.domain.lecture.enums.RegisterStatus;
import com.example.campuschool_backend.domain.user.UserEntity;
import com.example.campuschool_backend.dto.lecture.*;
import com.example.campuschool_backend.repository.LectureRepository;
import com.example.campuschool_backend.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;

    public Long createLecture(UserEntity user, CreateLectureForm createLectureForm) {
        Lecture lecture = Lecture.of(
                createLectureForm.getTitle(),
                createLectureForm.getRefImg(),
                createLectureForm.getDescription(),
                createLectureForm.getDay(),
                createLectureForm.getCategoryType(),
                createLectureForm.getDifficulty(),
                createLectureForm.getCurriculumList(),
                createLectureForm.getAvaliableTimeList(),
                user);
        Lecture savedLecture = lectureRepository.save(lecture);
        return savedLecture.getId();
    }

    @Override
    public List<LectureCardDTO> popularLectures() {
        return lectureRepository.findPopularLectures().stream().map((LectureCardDTO::from)).toList();
    }

    @Override
    public List<LectureCardDTO> recentLectures() {
        return lectureRepository.findRecentLectures().stream().map((LectureCardDTO::from)).toList();
    }

    @Override
    public Page<LectureCardDTO> Lectures(LectureSearchParam lectureSearchParam, Pageable pageable) {
        Page<Lecture> lectures = lectureRepository.findLectures(lectureSearchParam,pageable);
        return lectures.map((LectureCardDTO::from));
    }

    @Override
    public List<LectureCardDTO> getMyOpenLectures(UserEntity user) {
        List<Lecture> lectures = lectureRepository.findMyOpenLectures(user.getId());
        return lectures.stream().map((LectureCardDTO::from)).toList();
    }
    @Override
    public LectureDetailDTO getLectureDetail(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(()-> new RuntimeException());
        return LectureDetailDTO.builder()
                .id(lecture.getId())
                .day(lecture.getDay())
                .lectureDescription(lecture.getDescription())
                .lectureImage(lecture.getRefImage())
                .lectureTitle(lecture.getTitle())
                .curriculumEntityList(lecture.getCurriculumEntityList())
                .reviewList(lecture.getReviewList())
                .teacherDescription(lecture.getTeacher().getDescription().getDescription())
                .teacherEducation(lecture.getTeacher().getDescription().getEducation())
                .teacherHistory(lecture.getTeacher().getDescription().getHistory())
                .teacherImage(lecture.getTeacher().getImg())
                .teacherName(lecture.getTeacher().getName())
                .categoryType(lecture.getCategoryType())
                .avaliableTimeList(lecture.getAvaliableTimeList())
                .build();
    }
    @Transactional
    @Override
    public Long registerLecture(UserEntity userEntity, Long id) {
        Register register = Register.of(userEntity);
        Lecture lecture = lectureRepository.findById(id).orElseThrow(()-> new RuntimeException());
        if(lecture.checkDuplication(id)) throw new RuntimeException();
        if(lecture.checkMyLecture(userEntity.getId())) throw new RuntimeException();
        lecture.addRegister(register);
        return register.getId();
    }

    @Override
    public Page<NotificationDTO> getNotifications(Long id,Pageable pageable) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(()->new RuntimeException());
        Page<Notification> notifications = lectureRepository.findNotification(id,pageable);
        return notifications.map(((notification) -> NotificationDTO.from(lecture,notification)));
    }
    @Transactional
    @Override
    public NotificationDTO postNotifications(Long id, CreateNotificationForm createNotificationForm) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(()-> new RuntimeException());
        Notification notification = createNotificationForm.toNotification();
        lecture.addNotification(notification);
        return NotificationDTO.from(lecture,notification);
    }

    @Override
    public List<RegisterDTO> getRegisters(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(()->new RuntimeException());
        return lecture.getRegisterList().stream()
                .filter((register -> register.getStatus().equals(RegisterStatus.WAIT)))
                .map((RegisterDTO::from))
                .toList();
    }
    @Transactional
    @Override
    public Boolean approveRegister(Long id, Long registerId) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(()->new RuntimeException());
        Register find = lecture.getRegisterList().stream()
                .filter((register -> register.getId().equals(registerId)))
                .findFirst().orElseThrow(() -> new RuntimeException());
        find.setStatus(RegisterStatus.COMPLETE);
        return true;
    }

    @Override
    public List<LectureCardDTO> getMyRegisterLectures(UserEntity user) {
        List<Lecture> lectures = lectureRepository.findMyRegisterLectures(user.getId());
        return lectures.stream().map((LectureCardDTO::from)).toList();
    }

    @Override
    public List<LectureCardDTO> getMyWaitLectures(UserEntity user) {
        List<Lecture> lectures = lectureRepository.findMyWaitLectures(user.getId());
        return lectures.stream().map((LectureCardDTO::from)).toList();
    }
    @Transactional
    @Override
    public void addView(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(()->new RuntimeException());
        lecture.addView();
        System.out.println("hello");
        System.out.println(lecture.getViews());
    }

    @Override
    public Page<ReviewDTO> getReviews(Long id, Pageable pageable) {
        Page<Review> reviews = lectureRepository.findReview(id,pageable);
        return reviews.map((ReviewDTO::from));
    }
    @Transactional
    @Override
    public ReviewDTO postReview(Long id, Review review) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(()->new RuntimeException());
        lecture.addReview(review);
        return ReviewDTO.from(review);
    }

    @Override
    public List<ReviewCardDTO> getRecentReviews() {
        List<Review> reviews = lectureRepository.findRecentReviews();
        return reviews.stream().map((ReviewCardDTO::from)).toList();
    }

}
