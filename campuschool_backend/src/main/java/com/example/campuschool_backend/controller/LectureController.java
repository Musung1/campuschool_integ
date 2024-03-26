package com.example.campuschool_backend.controller;

import com.example.campuschool_backend.domain.user.UserEntity;
import com.example.campuschool_backend.dto.lecture.CreateLectureForm;
import com.example.campuschool_backend.dto.lecture.LectureCardDTO;
import com.example.campuschool_backend.dto.lecture.LectureDetailDTO;
import com.example.campuschool_backend.dto.lecture.LectureSearchParam;
import com.example.campuschool_backend.security.PrincipalUser;
import com.example.campuschool_backend.service.LectureService;
import com.example.campuschool_backend.util.FileUpload;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/class")
@RestController
public class LectureController {
    private final LectureService lectureService;

    @PostMapping("/open")
    public ResponseEntity<CreateLectureForm> createLecture(
            @RequestParam("img") MultipartFile img,
            @ModelAttribute CreateLectureForm lectureForm,
            HttpServletRequest request) throws IOException {
        String refImg = FileUpload.local(img,request);
        lectureForm.setRefImg(refImg);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        UserEntity userEntity = principalUser.getUser();
        Long id = lectureService.createLecture(userEntity,lectureForm);
        return ResponseEntity.ok(lectureForm);
    }
    @GetMapping("/popular")
    public ResponseEntity<List<LectureCardDTO>> getPopularLectures() {
        List<LectureCardDTO> lectureCardDTOList = lectureService.popularLectures();
        return ResponseEntity.ok(lectureCardDTOList);
    }
    @GetMapping("/recent")
    public ResponseEntity<List<LectureCardDTO>> getRecentLectures() {
        List<LectureCardDTO> lectureCardDTOList = lectureService.recentLectures();
        return ResponseEntity.ok(lectureCardDTOList);
    }
    @GetMapping("")
    public ResponseEntity<Page<LectureCardDTO>> getLectures(
            LectureSearchParam lectureSearchParam,
            @PageableDefault(size = 10) Pageable pageable) {
        System.out.println(lectureSearchParam.getCategoryType());
        pageable.getSort().get().forEach((order -> System.out.println(order.getProperty())));
        Page<LectureCardDTO> lectureCardDTOList = lectureService.Lectures(lectureSearchParam,pageable);
        return ResponseEntity.ok(lectureCardDTOList);
    }
    @GetMapping("/open")
    public ResponseEntity<List<LectureCardDTO>> getMyOpenLectures(@AuthenticationPrincipal UserDetails userDetails) {
        PrincipalUser principalUser = (PrincipalUser) userDetails;
        UserEntity user = principalUser.getUser();
        List<LectureCardDTO> myOpenLectures = lectureService.getMyOpenLectures(user);
        return ResponseEntity.ok(myOpenLectures);
    }
    @GetMapping("/register")
    public ResponseEntity<List<LectureCardDTO>> getMyRegisterLectures(@AuthenticationPrincipal UserDetails userDetails) {
        PrincipalUser principalUser = (PrincipalUser) userDetails;
        UserEntity user = principalUser.getUser();
        List<LectureCardDTO> myRegisterLectures = lectureService.getMyRegisterLectures(user);
        return ResponseEntity.ok(myRegisterLectures);
    }
    @GetMapping("/wait")
    public ResponseEntity<List<LectureCardDTO>> getMyWaitLectures(@AuthenticationPrincipal UserDetails userDetails) {
        PrincipalUser principalUser = (PrincipalUser) userDetails;
        UserEntity user = principalUser.getUser();
        List<LectureCardDTO> myRegisterLectures = lectureService.getMyWaitLectures(user);
        return ResponseEntity.ok(myRegisterLectures);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LectureDetailDTO> getLectureDetail(@PathVariable Long id) {
        LectureDetailDTO lectureDetailDTO = lectureService.getLectureDetail(id);
        lectureService.addView(id);
        return ResponseEntity.ok(lectureDetailDTO);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Long> registerLecture(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        PrincipalUser principalUser = (PrincipalUser) userDetails;
        UserEntity userEntity = principalUser.getUser();
        Long registerId = lectureService.registerLecture(userEntity,id);
        return ResponseEntity.ok(registerId);
    }

}
