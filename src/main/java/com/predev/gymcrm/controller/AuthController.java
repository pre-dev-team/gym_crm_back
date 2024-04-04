package com.predev.gymcrm.controller;

import com.predev.gymcrm.aop.annotation.ValidAspect;
import com.predev.gymcrm.dto.req.TrainerSignupReqDto;
import com.predev.gymcrm.dto.req.UserSigninReqDto;
import com.predev.gymcrm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.predev.gymcrm.dto.req.UserSignupReqDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    @ValidAspect
    @PostMapping("/user/signup")
    public ResponseEntity<?> userSignup(@RequestBody UserSignupReqDto userSignupReqDto, BindingResult bindingResult) {
        authService.userSignup(userSignupReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/user/signin")
    public ResponseEntity<?> userSignin(@RequestBody UserSigninReqDto userSigninReqDto) {
        authService.userSignin(userSigninReqDto);
        System.out.println(authService.userSignin(userSigninReqDto));
        return ResponseEntity.ok(authService.userSignin(userSigninReqDto));
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody LocalDateTime date) {
        System.out.println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return ResponseEntity.ok(date);
    }

}