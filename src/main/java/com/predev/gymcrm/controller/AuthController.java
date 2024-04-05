package com.predev.gymcrm.controller;

import com.predev.gymcrm.aop.annotation.ValidAspect;
import com.predev.gymcrm.dto.req.AccountSigninReqDto;
import com.predev.gymcrm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.predev.gymcrm.dto.req.AccountSignupReqDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ValidAspect
    @PostMapping("/user/signup")
    public ResponseEntity<?> userSignup(@RequestBody AccountSignupReqDto reqDto, BindingResult bindingResult) {
        System.out.println(reqDto);
        authService.userSignup(reqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/user/signin")
    public ResponseEntity<?> userSignin(@RequestBody AccountSigninReqDto reqDto) {
        authService.userSignin(reqDto);
        System.out.println(authService.userSignin(reqDto));
        return ResponseEntity.ok(authService.userSignin(reqDto));
    }


}