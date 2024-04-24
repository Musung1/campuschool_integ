package com.example.campuschool_backend.controller;

import com.example.campuschool_backend.domain.user.Description;
import com.example.campuschool_backend.domain.user.UserEntity;
import com.example.campuschool_backend.dto.auth.SignUpForm;
import com.example.campuschool_backend.dto.UserDTO;
import com.example.campuschool_backend.security.PrincipalUser;
import com.example.campuschool_backend.service.UserService;
import com.example.campuschool_backend.util.ErrorResult;
import com.example.campuschool_backend.util.FileUpload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpForm signUpForm) {
        UserDTO userDTO = userService.signUp(signUpForm);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> logIn(HttpServletRequest request, HttpServletResponse response) {
        String username = (String)request.getAttribute("username");
        UserDTO userDTO = userService.logIn(username);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logOut() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("logout");
    }
    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        PrincipalUser principalUser = (PrincipalUser) userDetails;
        UserDTO userDTO = UserDTO.from(principalUser.getUser());
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/user/name")
    public ResponseEntity<UserDTO> modifyUserName(@AuthenticationPrincipal UserDetails userDetails,
                                                  @RequestBody Map<String, String> body) {
        PrincipalUser principalUser = (PrincipalUser) userDetails;
        UserDTO userDTO = userService.modifyUserName(principalUser.getUser(),body.get("name"));
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/user/description")
    public ResponseEntity<UserDTO> modifyUserDescription(@AuthenticationPrincipal UserDetails userDetails,
                                                         @RequestBody Description description) {
        PrincipalUser principalUser = (PrincipalUser) userDetails;
        UserDTO userDTO = userService.modifyUserDescription(principalUser.getUser(), description);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/user/image")
    public ResponseEntity<UserDTO> modifyUserImage(@AuthenticationPrincipal UserDetails userDetails,
                                                   @RequestParam("img") MultipartFile multipartFile,
                                                   HttpServletRequest request) throws IOException {
        PrincipalUser principalUser = (PrincipalUser) userDetails;
        String url = FileUpload.local(multipartFile,request);
        UserDTO userDTO = userService.modifyUserImage(principalUser.getUser(), url);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        throw new RuntimeException("테스트 런타임 에러");
    }
}
