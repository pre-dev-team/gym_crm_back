package com.predev.gymcrm.controller;

import com.predev.gymcrm.aop.annotation.ValidAspect;
import com.predev.gymcrm.dto.req.*;
import com.predev.gymcrm.service.AuthService;
import com.predev.gymcrm.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/admin/signup")
    public ResponseEntity<?> adminSignup(@RequestBody AdminSignupReqDto reqDto) {
        if(authService.adminSignup(reqDto) == 0) {
            return ResponseEntity.badRequest().body(reqDto);
        }
        return ResponseEntity.created(null).body(reqDto);
    }

    @ValidAspect
    @PostMapping("/admin/trainer/signup")
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

    @PostMapping("/oauth2/merge")
    public ResponseEntity<?> oAuth2Merge(@RequestBody OAuth2MergeReqDto oAuth2MergeReqDto) {
        authService.oAuth2Merge(oAuth2MergeReqDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<?> getUsersByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(authService.searchAdminUsersByName(name));
    }

    @DeleteMapping("/admin/trainer/resign")
    public ResponseEntity<?> deleteTrainer(@RequestParam(value = "trainerId")int trainerId) {
        authService.deleteTrainer(trainerId);
        return ResponseEntity.ok(trainerId);
    }

    @PostMapping("/user/signin")
    public ResponseEntity<?> userSignin(@RequestBody AccountSigninReqDto reqDto) {
        return ResponseEntity.ok(authService.signIn(reqDto));
    }

    @DeleteMapping("/user/resign/{accountId}")
    public ResponseEntity<?> userResign(@PathVariable int accountId) {
        return ResponseEntity.ok(authService.deleteUser(accountId));
    }

}