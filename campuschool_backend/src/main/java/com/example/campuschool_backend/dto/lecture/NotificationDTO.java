package com.example.campuschool_backend.dto.lecture;

import com.example.campuschool_backend.domain.lecture.Lecture;
import com.example.campuschool_backend.domain.lecture.Notification;
import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NotificationDTO {
    private String title;
    private LocalDateTime createdAt;
    private String createdBy;
    private String content;
    public static NotificationDTO from(Lecture lecture, Notification notification) {
        return NotificationDTO.builder()
                .content(notification.getContent())
                .createdAt(notification.getCreatedAt())
                .createdBy(lecture.getTeacher().getName())
                .title(notification.getTitle())
                .build();
    }
}
