package com.example.campuschool_backend.domain.lecture;

import com.example.campuschool_backend.domain.auditing.AuditingField;
import com.example.campuschool_backend.domain.lecture.enums.RegisterStatus;
import com.example.campuschool_backend.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Register extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private RegisterStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    public static Register of(UserEntity user) {
        Register register = new Register();
        register.setStatus(RegisterStatus.WAIT);
        register.setUser(user);
        return register;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Register register = (Register) o;
        return Objects.equals(id, register.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

}
