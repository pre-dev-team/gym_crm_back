package com.predev.gymcrm.controller;

import com.predev.gymcrm.aop.annotation.ValidAspect;
import com.predev.gymcrm.dto.req.UserSignupReqDto;
import com.predev.gymcrm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    @ValidAspect
    @PostMapping("/user/signup")
    public ResponseEntity<?> userSignup(@RequestBody UserSignupReqDto userSignupReqDto, BindingResult bindingResult) {
        authService.userSignup(userSignupReqDto);
        return ResponseEntity.created(null).body(true);
    }
}
