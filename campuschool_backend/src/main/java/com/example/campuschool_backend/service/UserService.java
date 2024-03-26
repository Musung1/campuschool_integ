package com.example.campuschool_backend.service;

import com.example.campuschool_backend.domain.user.Description;
import com.example.campuschool_backend.domain.user.UserEntity;
import com.example.campuschool_backend.dto.auth.SignUpForm;
import com.example.campuschool_backend.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    public UserDTO signUp(SignUpForm signUpForm);
    public UserDTO logIn(String username);
    public UserDTO modifyUserName(UserEntity userEntity, String name);
    public UserDTO modifyUserDescription(UserEntity userEntity, Description description);
    public UserDTO modifyUserImage(UserEntity userEntity,String url);

}
