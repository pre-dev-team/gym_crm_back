package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.WorkoutRoutine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkoutRoutineMapper {
    int saveRoutines(List<WorkoutRoutine> workoutRoutines);
    List<WorkoutRoutine> findWorkoutRoutines(int reservationId);

}
