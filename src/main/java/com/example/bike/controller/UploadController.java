package com.example.bike.controller;

import com.example.bike.service.AmazonS3Service;
import com.example.bike.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.VERSION_1 + "/upload")
public class UploadController {
    private final AmazonS3Service amazonS3Service;

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestPart MultipartFile multipartFile) {
        return amazonS3Service.uploadImage(multipartFile);
    }
}


