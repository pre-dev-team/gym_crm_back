package com.predev.gymcrm.controller;

import com.predev.gymcrm.aop.annotation.ValidAspect;
import com.predev.gymcrm.dto.req.AdminEditPasswordReqDto;
import com.predev.gymcrm.dto.req.TrainerEditPasswordReqDto;
import com.predev.gymcrm.dto.req.UserEditPasswordReqDto;
import com.predev.gymcrm.security.Principal;
import com.predev.gymcrm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) authentication.getPrincipal();
        return ResponseEntity.ok(principal);
    }

    @ValidAspect
    @PutMapping("/user/password")
    public ResponseEntity<?> updatePassword(@RequestBody UserEditPasswordReqDto reqDto) {
        return ResponseEntity.ok(accountService.editAccountPassword(reqDto));
    }

    @PutMapping("/admin/password")
    public ResponseEntity<?> updateAdminPassword(@RequestBody AdminEditPasswordReqDto reqDto) {
        return ResponseEntity.ok(accountService.editAdminPassword(reqDto));
    }

    @PutMapping("/trainer/password")
    public ResponseEntity<?> updateTrainerPassword(@RequestBody TrainerEditPasswordReqDto reqDto) {
        return ResponseEntity.ok(accountService.editTrainerPassword(reqDto));
    }

    @GetMapping("/user/info")
    public ResponseEntity<?> getUserInfo(@RequestParam(value = "accountId") int accountId) {
        return ResponseEntity.ok(accountService.searchAccountInfoByAccountId(accountId));
    }

    @GetMapping("/trainer/id")
    public ResponseEntity<?> getTrainerId(@RequestParam(value = "accountId") int accountId) {
        return ResponseEntity.ok(accountService.searchTrainerId(accountId));
    }
}
