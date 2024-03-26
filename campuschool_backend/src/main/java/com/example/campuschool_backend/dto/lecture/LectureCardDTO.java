package com.example.campuschool_backend.dto.lecture;
import com.example.campuschool_backend.domain.lecture.Lecture;
import com.example.campuschool_backend.domain.lecture.enums.CategoryType;
import com.example.campuschool_backend.domain.lecture.enums.Difficulty;
import com.example.campuschool_backend.domain.lecture.enums.LectureStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LectureCardDTO {
    private Long id;
    private String title;
    private CategoryType categoryType;
    private Difficulty difficulty;
    private String refImage;
    private String description;
    private LectureStatus lectureStatus;
    private int views;
    private String teacherName;
    private int averageRating;
    private int reviews;

    public static LectureCardDTO from(Lecture lecture) {
        return LectureCardDTO.builder()
                .id(lecture.getId())
                .title(lecture.getTitle())
                .categoryType(lecture.getCategoryType())
                .difficulty(lecture.getDifficulty())
                .refImage(lecture.getRefImage())
                .description(lecture.getDescription())
                .lectureStatus(lecture.getLectureStatus())
                .views(lecture.getViews())
                .teacherName(lecture.getTeacher().getName())
                .reviews(lecture.getReviewList().size())
                .averageRating(lecture.getAverageRating())
                .build();
    }
}
