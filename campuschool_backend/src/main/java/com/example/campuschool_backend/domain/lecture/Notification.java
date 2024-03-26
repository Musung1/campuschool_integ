package com.example.campuschool_backend.domain.lecture;

import com.example.campuschool_backend.domain.auditing.AuditingField;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Notification extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String title;
    @Setter
    @Getter
    private String content;

    protected Notification() {}
    public static Notification of(String title, String content) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setContent(content);
        return notification;
    }
}
