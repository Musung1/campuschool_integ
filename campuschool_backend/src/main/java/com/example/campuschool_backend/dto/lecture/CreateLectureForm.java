package com.example.campuschool_backend.dto.lecture;

import com.example.campuschool_backend.domain.lecture.AvaliableTime;
import com.example.campuschool_backend.domain.lecture.CurriculumEntity;
import com.example.campuschool_backend.domain.lecture.enums.CategoryType;
import com.example.campuschool_backend.domain.lecture.enums.Difficulty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateLectureForm {
    private String title;
    private String refImg;
    private int day;
    private String description;
    private CategoryType categoryType;
    private Difficulty difficulty;
    private List<CurriculumEntity> curriculumList;
    private List<AvaliableTime> avaliableTimeList;

}
