package com.predev.gymcrm.controller;

import com.predev.gymcrm.aop.annotation.ValidAspect;
import com.predev.gymcrm.dto.req.AccountSigninReqDto;
import com.predev.gymcrm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.predev.gymcrm.dto.req.AccountSignupReqDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ValidAspect
    @PostMapping("/user/signup")
    public ResponseEntity<?> userSignup(@Valid @RequestBody AccountSignupReqDto reqDto, BindingResult bindingResult) {
        authService.userSignup(reqDto);
        return ResponseEntity.created(null).body(reqDto);
    }

    @PostMapping("/user/signin")
    public ResponseEntity<?> userSignin(@RequestBody AccountSigninReqDto reqDto) {
        authService.userSignin(reqDto);
        return ResponseEntity.ok(authService.userSignin(reqDto));
    }


}