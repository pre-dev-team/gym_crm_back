package com.predev.gymcrm.controller;

import com.predev.gymcrm.aop.annotation.ValidAspect;
import com.predev.gymcrm.dto.req.UserSigninReqDto;
import com.predev.gymcrm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.predev.gymcrm.dto.req.UserSignupReqDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ValidAspect
    @PostMapping("/user/signup")
    public ResponseEntity<?> userSignup(@RequestBody UserSignupReqDto userSignupReqDto, BindingResult bindingResult) {
        System.out.println(userSignupReqDto);
        authService.userSignup(userSignupReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/user/signin")
    public ResponseEntity<?> userSignin(@RequestBody UserSigninReqDto userSigninReqDto) {
        authService.userSignin(userSigninReqDto);
        System.out.println(authService.userSignin(userSigninReqDto));
        return ResponseEntity.ok(authService.userSignin(userSigninReqDto));
    }


}