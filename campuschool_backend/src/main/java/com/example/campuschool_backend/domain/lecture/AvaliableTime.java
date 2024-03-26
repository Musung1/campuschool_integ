package com.example.campuschool_backend.domain.lecture;

import com.example.campuschool_backend.domain.auditing.AuditingField;
import com.example.campuschool_backend.domain.lecture.enums.Days;
import com.example.campuschool_backend.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class AvaliableTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Days day;
    private String time;
    private Long registerBy;

    public static AvaliableTime of(Days day, String time) {
        AvaliableTime avaliableTime = new AvaliableTime();
        avaliableTime.setDay(day);
        avaliableTime.setTime(time);
        return avaliableTime;
    }
}
