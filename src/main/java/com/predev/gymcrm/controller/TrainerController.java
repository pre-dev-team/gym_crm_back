package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.resp.SearchMyMembersRespDto;
import com.predev.gymcrm.service.TrainerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/mypage/members")
    public ResponseEntity<?> getMyMembers(@RequestParam(value = "accountId") int trainerAccountId) {
        return ResponseEntity.ok(trainerService.selectMyMembers(trainerAccountId));
    }
}
