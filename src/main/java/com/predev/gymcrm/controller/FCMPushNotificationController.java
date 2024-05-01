package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.FCMTokenReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class FCMPushNotificationController {

    @PostMapping("/register")
    public ResponseEntity<?> register(FCMTokenReqDto tokenReqDto) {
        return ResponseEntity.ok(tokenReqDto);
    }
}
