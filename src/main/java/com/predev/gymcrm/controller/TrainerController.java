package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.RoutineMakeReqDto;
import com.predev.gymcrm.dto.req.TrainerHolidayReqDto;
import com.predev.gymcrm.dto.req.UpdateTrainerProfileImgReqDto;
import com.predev.gymcrm.dto.resp.SearchMyMembersRespDto;
import com.predev.gymcrm.dto.resp.TrainerInfoRespDto;
import com.predev.gymcrm.service.TrainerService;
import com.predev.gymcrm.service.WorkoutRoutineService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getAllTrainerInfo(@RequestParam(value = "accountId") int accountId) {
        TrainerInfoRespDto trainerInfo = trainerService.getAllTrainerInfo(accountId);

        return ResponseEntity.ok(trainerInfo);
    }

    @GetMapping("/trainers/user")
    public ResponseEntity<?> getTrainers() {
        return ResponseEntity.ok(trainerService.getTrainersForReservation());
    }

    @PutMapping("/mypage/trainerimg")
    public ResponseEntity<?> updateTrainerImg(@RequestBody UpdateTrainerProfileImgReqDto reqDto) {
        trainerService.updateTrainerProfileImg(reqDto);
        return ResponseEntity.ok(true);
    }
    // trainer routine
    @PostMapping("/routine")
    public ResponseEntity<?> makeRoutine (@RequestBody List<RoutineMakeReqDto> routineMakeReqDtos) {
        System.out.println(routineMakeReqDtos);
        trainerService.makeRoutine(routineMakeReqDtos);
        return ResponseEntity.ok(routineMakeReqDtos);
    }

}
