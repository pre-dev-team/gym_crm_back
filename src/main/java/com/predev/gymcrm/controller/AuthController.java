package com.predev.gymcrm.controller;

import com.predev.gymcrm.aop.annotation.ValidAspect;
import com.predev.gymcrm.dto.req.AccountSigninReqDto;
import com.predev.gymcrm.dto.req.EditPasswordReqDto;
import com.predev.gymcrm.dto.req.OAuth2SignupReqDto;
import com.predev.gymcrm.service.AuthService;
import com.predev.gymcrm.service.ReservationService;
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

    @Autowired
    private ReservationService reservationService;

    @ValidAspect
    @PostMapping("/user/signup")
    public ResponseEntity<?> userSignup(@Valid @RequestBody AccountSignupReqDto reqDto, BindingResult bindingResult) {
        authService.userSignup(reqDto);
        return ResponseEntity.created(null).body(reqDto);
    }

    @ValidAspect
    @PostMapping("/trainer/signup")
    public ResponseEntity<?> trainerSignup(@Valid @RequestBody AccountSignupReqDto reqDto, BindingResult bindingResult) {
        authService.trainerSignup(reqDto);
        return ResponseEntity.created(null).body(reqDto);
    }
    @ValidAspect
    @PostMapping("/oauth2/signup")
    public ResponseEntity<?> oAuth2Signup(@Valid @RequestBody OAuth2SignupReqDto oAuth2SignupReqDto, BindingResult bindingResult) {
        authService.oAuth2Signup(oAuth2SignupReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/account/signin")
    public ResponseEntity<?> userSignin(@RequestBody AccountSigninReqDto reqDto) {
        authService.Signin(reqDto);
        return ResponseEntity.ok(authService.Signin(reqDto));
    }

    @GetMapping("/account/trainerid")
    public ResponseEntity<?> getTrainerId(@RequestParam(value = "accountId") int accountId) {
        return ResponseEntity.ok(reservationService.getTrainerId(accountId));
    }

    @GetMapping("/account/myinfo")
    public ResponseEntity<?> getMyInfo(@RequestParam(value = "accountId") int accountId) {
        return ResponseEntity.ok(authService.getAccountInfoByAccountId(accountId));
    }


}