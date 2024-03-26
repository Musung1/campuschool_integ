package com.example.campuschool_backend.controller;

import com.example.campuschool_backend.util.FileUpload;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;

@RestController
public class FileController {
    @RequestMapping(value = "/uploadfile/{file_name:.+}", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<byte[]> getImage(@PathVariable("file_name") String file_name, HttpServletRequest request) throws Exception {
        try {
            String root_path = FileUpload.path(request);
            File file = new File(root_path + file_name);
            byte[] imageBytes = Files.readAllBytes(file.toPath());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        } catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
