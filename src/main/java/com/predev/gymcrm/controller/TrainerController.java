package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.resp.SearchMyMembersRespDto;
import com.predev.gymcrm.dto.resp.TrainerInfoRespDto;
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
    @GetMapping("/mypage/trainerInfo")
    public ResponseEntity<?> getAllTrainerInfo(@RequestParam(value = "trainerId") int trainerId) {
        TrainerInfoRespDto trainerInfo = trainerService.getAllTrainerInfo(trainerId);
        if (trainerInfo != null) {
            return ResponseEntity.ok(trainerInfo);
        } else {
            return ResponseEntity.notFound().build(); // 트레이너가 존재하지 않을 경우 404 응답
        }
    }
}
