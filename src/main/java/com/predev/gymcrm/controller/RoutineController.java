package com.predev.gymcrm.controller;


import com.predev.gymcrm.dto.req.RoutineMakeReqDto;
import com.predev.gymcrm.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/routine")
public class RoutineController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping("/trainer")
    public ResponseEntity<?> makeRoutine (@RequestBody List<RoutineMakeReqDto> routineMakeReqDtos) {

        trainerService.makeRoutine(routineMakeReqDtos);

        return ResponseEntity.ok(routineMakeReqDtos);
    }
}
