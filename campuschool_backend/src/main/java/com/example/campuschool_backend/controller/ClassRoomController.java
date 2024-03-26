package com.example.campuschool_backend.controller;

import com.example.campuschool_backend.domain.lecture.Register;
import com.example.campuschool_backend.dto.lecture.CreateNotificationForm;
import com.example.campuschool_backend.dto.lecture.NotificationDTO;
import com.example.campuschool_backend.dto.lecture.RegisterDTO;
import com.example.campuschool_backend.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/user/open/{id}/")
@RestController
public class ClassRoomController {
    private final LectureService lectureService;
    @GetMapping("notification")
    public ResponseEntity<Page<NotificationDTO>> getNotifications(@PathVariable Long id,
                                                                  @PageableDefault Pageable pageable) {
        Page<NotificationDTO> notificationDTOS = lectureService.getNotifications(id,pageable);
        return ResponseEntity.ok(notificationDTOS);
    }
    @PostMapping("notification")
    public ResponseEntity<NotificationDTO> postNotifications(@PathVariable Long id,
                                                             @RequestBody CreateNotificationForm createNotificationForm) {
        NotificationDTO notificationDTO = lectureService.postNotifications(id,createNotificationForm);
        return ResponseEntity.ok(notificationDTO);
    }
    @GetMapping("register")
    public ResponseEntity<List<RegisterDTO>> getRegisters(@PathVariable Long id){
        List<RegisterDTO> registerDTOS = lectureService.getRegisters(id);
        return ResponseEntity.ok(registerDTOS);
    }
    @PostMapping("register/{registerId}")
    public ResponseEntity<Boolean> approveRegister(@PathVariable Long id, @PathVariable Long registerId){
        Boolean approved = lectureService.approveRegister(id,registerId);
        return ResponseEntity.ok(approved);
    }
}
