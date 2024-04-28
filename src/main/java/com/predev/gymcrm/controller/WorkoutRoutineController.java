package com.predev.gymcrm.controller;


import com.predev.gymcrm.dto.req.RoutineMakeReqDto;
import com.predev.gymcrm.service.WorkoutRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routine")
public class WorkoutRoutineController {

    @Autowired
    private WorkoutRoutineService workoutRoutineService;

    @PostMapping("/trainer")
    public ResponseEntity<?> makeRoutine(@RequestBody List<RoutineMakeReqDto> routineMakeReqDtos) {
        return ResponseEntity.ok(workoutRoutineService.makeRoutine(routineMakeReqDtos));
    }

    @GetMapping("/trainer")
    public ResponseEntity<?> findReservationRoutine(int reservationId) {
        return ResponseEntity.ok(workoutRoutineService.findRoutinesByReservationId(reservationId));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editRoutine(@RequestBody List<RoutineMakeReqDto> routineMakeReqDtos) {
        return ResponseEntity.ok(workoutRoutineService.EditRoutines(routineMakeReqDtos));
    }
}
