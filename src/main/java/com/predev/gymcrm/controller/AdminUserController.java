package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.SearchUserReqDto;
import com.predev.gymcrm.entity.TrainerReview;
import com.predev.gymcrm.service.AdminService;
import com.predev.gymcrm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private AdminService adminService;


    @GetMapping("/reviews")
    public ResponseEntity<?> findAllTrainerReviews() {
        System.out.println("work");
        List<TrainerReview> reviews = adminService.findAllTrainerReviews();
        System.out.println(reviews);
        return ResponseEntity.ok(reviews);
    }
//    @GetMapping("/users")
//    public ResponseEntity<?> searchUsers(SearchUserReqDto searchUserReqDto) {
//        return ResponseEntity.ok(adminService.searchUsers(searchUserReqDto));
//    }
}
