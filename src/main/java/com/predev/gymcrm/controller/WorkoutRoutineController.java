package com.predev.gymcrm.controller;


import com.predev.gymcrm.dto.req.TrainerAddRoutineReqDto;
import com.predev.gymcrm.service.WorkoutRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/routine")
public class WorkoutRoutineController {

    @Autowired
    private WorkoutRoutineService workoutRoutineService;

    @PostMapping("/trainer")
    public ResponseEntity<?> addRoutine(@RequestBody List<TrainerAddRoutineReqDto> trainerAddRoutineReqDtos) {
        int successCount = workoutRoutineService.insertRoutine(trainerAddRoutineReqDtos);
        if(successCount == 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "이미 등록된 루틴이 존재합니다"));
        }
        return ResponseEntity.ok(successCount);
    }

    @GetMapping("/user")
    public ResponseEntity<?> findReservationRoutine(int reservationId) {
        return ResponseEntity.ok(workoutRoutineService.searchRoutinesByReservationId(reservationId));
    }

    @PostMapping("/trainer/addtion")
    public ResponseEntity<?> updateRoutine(@RequestBody List<TrainerAddRoutineReqDto> TrainerAddRoutineReqDto) {
        return ResponseEntity.ok(workoutRoutineService.editRoutines(TrainerAddRoutineReqDto));
    }
}
