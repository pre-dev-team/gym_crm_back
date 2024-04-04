package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.TrainerSignupReqDto;
import com.predev.gymcrm.dto.req.UserSigninReqDto;
import com.predev.gymcrm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.predev.gymcrm.dto.req.UserSignupReqDto;
import org.springframework.validation.BindingResult;
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

    @PostMapping("/user/signup")
    public ResponseEntity<?> userSignup(@RequestBody UserSignupReqDto userSignupReqDto, BindingResult bindingResult) {
        authService.userSignup(userSignupReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/user/signin")
    public ResponseEntity<?> userSignin(@RequestBody UserSigninReqDto userSigninReqDto) {
        return ResponseEntity.ok(authService.userSignin(userSigninReqDto));
    }
}