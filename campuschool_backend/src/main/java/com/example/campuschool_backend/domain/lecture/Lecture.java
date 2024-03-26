package com.example.campuschool_backend.domain.lecture;

import com.example.campuschool_backend.domain.auditing.AuditingField;
import com.example.campuschool_backend.domain.lecture.enums.CategoryType;
import com.example.campuschool_backend.domain.lecture.enums.Difficulty;
import com.example.campuschool_backend.domain.lecture.enums.LectureStatus;
import com.example.campuschool_backend.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Lecture extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(value = EnumType.STRING)
    private CategoryType categoryType;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    private String refImage;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private LectureStatus lectureStatus;
    private int day;
    private int views;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity teacher;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id")
    private List<Register> registerList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id")
    private List<CurriculumEntity> curriculumEntityList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id")
    private List<AvaliableTime> avaliableTimeList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture")
    private List<Review> reviewList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id")
    private List<Notification> notificationList = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lecture lecture = (Lecture) o;
        return Objects.equals(id, lecture.id);
    }
    public int getAverageRating() {
        int result = 0;
        if(reviewList.size() == 0) return 0;
        for (int i = 0; i < reviewList.size(); i++) {
            result += reviewList.get(i).getRating();
        }
        return result/reviewList.size();
    }
    public void addView() {
        setViews(views+1);
    }
    public void addReview(Review review) {
        reviewList.add(review);
        review.setLecture(this);
    }
    public void addRegister(Register register) {
        registerList.add(register);
    }
    public void addNotification(Notification notification) {
        notificationList.add(notification);
    }
    public boolean checkDuplication(Long id) {
        for (Register register : registerList) {
            if (register.getUser().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkMyLecture(Long id) {
        return teacher.getId().equals(id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    protected Lecture() {}
    public static Lecture of(String title, CategoryType categoryType, Difficulty difficulty,UserEntity user) {
        Lecture lecture = new Lecture();
        lecture.setTitle(title);
        lecture.setCategoryType(categoryType);
        lecture.setDifficulty(difficulty);
        lecture.setTeacher(user);
        return lecture;
    }
    public static Lecture of(String title,
                             String refImage,
                             String description,
                             int day,
                             CategoryType categoryType,
                             Difficulty difficulty,
                             List<CurriculumEntity> curriculumEntityList,
                             List<AvaliableTime> avaliableTimeList,
                             UserEntity user) {
        Lecture lecture = new Lecture();
        lecture.setTitle(title);
        lecture.setRefImage(refImage);
        lecture.setDescription(description);
        lecture.setDay(day);
        lecture.setCategoryType(categoryType);
        lecture.setDifficulty(difficulty);
        lecture.setCurriculumEntityList(curriculumEntityList);
        lecture.setAvaliableTimeList(avaliableTimeList);
        lecture.setTeacher(user);
        lecture.setLectureStatus(LectureStatus.PROGRESS);
        return lecture;
    }
}
