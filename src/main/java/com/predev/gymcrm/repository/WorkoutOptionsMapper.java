package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Workout;
import com.predev.gymcrm.entity.WorkoutCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkoutOptionsMapper {
    public List<WorkoutCategory> findAllWorkoutCategory();
    public List<Workout> findAllWorkoutByWorkoutCategoryId(int workoutCategoryId);
}
