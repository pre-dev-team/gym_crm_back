package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.RoutineMakeReqDto;
import com.predev.gymcrm.entity.WorkoutRoutine;
import com.predev.gymcrm.repository.WorkoutRoutineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutRoutineService {

    @Autowired
    WorkoutRoutineMapper workoutRoutineMapper;


    public void makeRoutine(List<RoutineMakeReqDto> routineMakeReqDtos) {
        List<WorkoutRoutine> workoutRoutines = routineMakeReqDtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
        int successCount = workoutRoutineMapper.saveRoutines(workoutRoutines);
        System.out.println(successCount);
    }
}
