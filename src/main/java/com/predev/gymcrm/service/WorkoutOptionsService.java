package com.predev.gymcrm.service;

import com.predev.gymcrm.entity.Trainer;
import com.predev.gymcrm.entity.Workout;
import com.predev.gymcrm.entity.WorkoutCategory;
import com.predev.gymcrm.repository.WorkoutOptionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutOptionsService {

    @Autowired
    private WorkoutOptionsMapper workoutOptionsMapper;


    public List<WorkoutCategory> getAllWorkoutCategory(){
        return workoutOptionsMapper.getAllWorkoutCategory();
    }

    public List<Workout> findAllWorkoutByWorkoutCategoryId(int workoutCategoryId) {
        List<Workout> workouts = workoutOptionsMapper.findAllWorkoutByWorkoutCategoryId(workoutCategoryId);
        return workouts;
    }

}
