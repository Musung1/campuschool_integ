package com.example.campuschool_backend.service.impl;

import com.example.campuschool_backend.domain.user.Description;
import com.example.campuschool_backend.domain.user.LoginType;
import com.example.campuschool_backend.domain.user.RoleType;
import com.example.campuschool_backend.domain.user.UserEntity;
import com.example.campuschool_backend.dto.auth.SignUpForm;
import com.example.campuschool_backend.dto.UserDTO;
import com.example.campuschool_backend.repository.UserRepository;
import com.example.campuschool_backend.service.UserService;
import com.example.campuschool_backend.util.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDTO signUp(SignUpForm signUpForm) {
        UserEntity user = UserEntity.of(
                signUpForm.getUsername(),
                passwordEncoder.encode(signUpForm.getPassword()),
                signUpForm.getName(),
                LoginType.EMAIL,
                RoleType.USER
        );
        UserEntity savedUser = userRepository.save(user);
        UserDTO userDTO = UserDTO.from(savedUser);
        return userDTO;
    }

    @Override
    public UserDTO logIn(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException());
        return UserDTO.from(userEntity);
    }

    @Override
    @Transactional
    public UserDTO modifyUserName(UserEntity userEntity, String name) {
        UserEntity user = userRepository.findById(userEntity.getId()).orElseThrow(()-> new RuntimeException());
        user.setName(name);
        return UserDTO.from(user);
    }

    @Override
    @Transactional
    public UserDTO modifyUserDescription(UserEntity userEntity, Description description) {
        UserEntity user = userRepository.findById(userEntity.getId()).orElseThrow(()-> new RuntimeException());
        user.setDescription(description);
        return UserDTO.from(user);
    }

    @Override
    @Transactional
    public UserDTO modifyUserImage(UserEntity userEntity, String url) {
        UserEntity user = userRepository.findById(userEntity.getId()).orElseThrow(()-> new RuntimeException());
        user.setImg(url);
        return UserDTO.from(user);
    }

}
