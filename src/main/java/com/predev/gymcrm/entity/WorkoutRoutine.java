package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.SearchWorkoutRoutineRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkoutRoutine {
    private int workoutRoutineId;
    private int reservationId;
    private int workoutId;
    private int workoutRoutineCount;
    private int workoutRoutineSet;
    private int workoutRoutineWeight;
    private int workoutRoutineOrder;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Workout workout;

    public SearchWorkoutRoutineRespDto toSearchWorkoutRoutineRespDto() {
        return SearchWorkoutRoutineRespDto.builder()
                .workoutRoutineId(workoutRoutineId)
                .reservationId(reservationId)
                .workoutId(workoutId)
                .workoutName(workout.getWorkoutName())
                .workoutCategoryId(workout.getWorkoutCategory().getWorkoutCategoryId())
                .workoutCategoryName(workout.getWorkoutCategory().getWorkoutCategoryName())
                .workoutRoutineCount(workoutRoutineCount)
                .workoutRoutineSet(workoutRoutineSet)
                .workoutRoutineWeight(workoutRoutineWeight)
                .workoutRoutineOrder(workoutRoutineOrder)
                .build();
    }
}
