package com.predev.gymcrm.controller;


import com.predev.gymcrm.dto.req.RoutineMakeReqDto;
import com.predev.gymcrm.service.TrainerService;
import com.predev.gymcrm.service.WorkoutRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routine")
public class RoutineController {

    @Autowired
    private WorkoutRoutineService workoutRoutineService;

    @PostMapping("/trainer")
    public ResponseEntity<?> makeRoutine(@RequestBody List<RoutineMakeReqDto> routineMakeReqDtos) {
        workoutRoutineService.makeRoutine(routineMakeReqDtos);
        return ResponseEntity.ok(routineMakeReqDtos);
    }

    @GetMapping("trainer")
    public ResponseEntity<?> findReservationRoutine(int reservationId) {
        return ResponseEntity.ok(reservationId);
    }
}
