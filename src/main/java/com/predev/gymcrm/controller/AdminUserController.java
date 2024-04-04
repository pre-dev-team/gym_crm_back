package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.SearchTrainerReqDto;
import com.predev.gymcrm.dto.req.SearchUserReqDto;
import com.predev.gymcrm.service.AdminService;
import com.predev.gymcrm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<?> searchUsers(SearchUserReqDto searchUserReqDto) {
        return ResponseEntity.ok(adminService.searchUsers(searchUserReqDto));
    }

    @GetMapping
    public ResponseEntity<?> searchTrainers(SearchTrainerReqDto searchTrainerReqDto) {
        return ResponseEntity.ok(adminService.searchTrainers(searchTrainerReqDto));
    }
}
