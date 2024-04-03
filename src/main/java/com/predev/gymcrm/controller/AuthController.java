package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.TrainerSignupReqDto;
import com.predev.gymcrm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/trainer/signup")
    public ResponseEntity<?> trainerSignup(@RequestBody TrainerSignupReqDto trainerSignupReqDto) {
        authService.trainerSignup(trainerSignupReqDto);
        return ResponseEntity.created(null).body(true);
    }

}
