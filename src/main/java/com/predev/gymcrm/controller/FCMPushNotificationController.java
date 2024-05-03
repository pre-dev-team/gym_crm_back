package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.FCMTokenReqDto;
import com.predev.gymcrm.security.Principal;
import com.predev.gymcrm.service.FCMPushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class FCMPushNotificationController {

    @Autowired
    FCMPushNotificationService fcmPushNotificationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody FCMTokenReqDto tokenReqDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) authentication.getPrincipal();
        int accountId = principal.getAccountId();
        fcmPushNotificationService.register(accountId, tokenReqDto.getFcmToken());
        return ResponseEntity.ok(tokenReqDto);
    }
}
