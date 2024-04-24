package com.example.campuschool_backend.dto.auth;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpForm {
    @Email(message = "이메일 양식이 아닙니다")
    private String username;
    private String password;
    private String name;
}
