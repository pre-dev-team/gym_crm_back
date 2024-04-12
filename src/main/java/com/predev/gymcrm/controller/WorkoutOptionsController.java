package com.predev.gymcrm.controller;

import com.predev.gymcrm.entity.Workout;
import com.predev.gymcrm.service.WorkoutOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/options")
public class WorkoutOptionsController {

    @Autowired
    private WorkoutOptionsService workoutOptionsService;

    @GetMapping("/category")
    public ResponseEntity<?>  getWorkoutCategory(){
        return ResponseEntity.ok(workoutOptionsService.getAllWorkoutCategory());
    }

    @GetMapping("/category/workouts")
    public ResponseEntity<?> getWorkouts(@RequestParam(value = "workoutCategoryId")int workoutCategoryId) {
        List<Workout> workouts = workoutOptionsService.findAllWorkoutByWorkoutCategoryId(workoutCategoryId);

        return ResponseEntity.ok(workouts);
    }
}
