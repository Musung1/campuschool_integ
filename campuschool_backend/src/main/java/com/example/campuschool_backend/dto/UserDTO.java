package com.example.campuschool_backend.dto;

import com.example.campuschool_backend.domain.user.Description;
import com.example.campuschool_backend.domain.user.LoginType;
import com.example.campuschool_backend.domain.user.RoleType;
import com.example.campuschool_backend.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String img;
    private String portfolioImg;
    private RoleType roleType;
    private LoginType loginType;
    private Description description;

    public static UserDTO from(UserEntity user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .img(user.getImg())
                .portfolioImg(user.getPortfolioImg())
                .roleType(user.getRoleType())
                .loginType(user.getLoginType())
                .description(user.getDescription())
                .build();
    }
}
