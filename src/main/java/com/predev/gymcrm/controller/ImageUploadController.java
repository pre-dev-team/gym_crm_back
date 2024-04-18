package com.predev.gymcrm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/trainer")
public class ImageUploadController {

//    @Value("${file.upload-dir}")
    private String uploadDir = "";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body("Image file is empty");
        }

        try {
            // 이미지 파일 이름 생성
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

            // 이미지를 저장할 경로 설정
            String filePath = uploadDir + File.separator + fileName;
            File dest = new File(filePath);

            // 이미지 파일 저장
            image.transferTo(dest);

            // 저장된 이미지의 URL 반환
            String imageUrl = "/api/uploads/" + fileName;
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }
}

