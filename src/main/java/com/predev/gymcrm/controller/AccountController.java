package com.predev.gymcrm.controller;

import com.predev.gymcrm.aop.annotation.ValidAspect;
import com.predev.gymcrm.dto.req.AdminPasswordChangeReqDto;
import com.predev.gymcrm.dto.req.EditPasswordReqDto;
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
    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody EditPasswordReqDto reqDto) {
        accountService.editAccountPassword(reqDto);
        return ResponseEntity.ok(reqDto);
    }

    @PutMapping("/admin/edit/password")
    public ResponseEntity<?> updateAdminPassword(@RequestBody AdminPasswordChangeReqDto reqDto) {
        return ResponseEntity.ok(accountService.editAdminPassword(reqDto));
    }

    @GetMapping("/myinfo")
    public ResponseEntity<?> getMyInfo(@RequestParam(value = "accountId") int accountId) {
        return ResponseEntity.ok(accountService.selectAccountInfoByAccountId(accountId));
    }
}
