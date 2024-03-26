package com.example.campuschool_backend.dto.lecture;

import com.example.campuschool_backend.domain.lecture.enums.CategoryType;
import com.example.campuschool_backend.domain.lecture.enums.Difficulty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LectureSearchParam {
    private String keyword;
    private CategoryType categoryType;
    private Difficulty difficulty;
}
