package com.example.campuschool_backend.dto.auth;

import com.example.campuschool_backend.domain.user.LoginType;
import com.example.campuschool_backend.domain.user.UserEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignUpForm {
    private String username;
    private String password;
    private String name;
}
