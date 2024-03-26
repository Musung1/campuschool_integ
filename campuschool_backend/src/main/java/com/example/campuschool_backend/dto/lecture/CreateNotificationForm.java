package com.example.campuschool_backend.dto.lecture;

import com.example.campuschool_backend.domain.lecture.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateNotificationForm {
    private String title;
    private String content;
    public Notification toNotification() {
        return Notification.of(title,content);
    }
}
