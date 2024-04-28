package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.WorkoutRoutine;
import lombok.Data;

@Data
public class RoutineMakeReqDto {
    private int reservationId;
    private int workoutId;
    private int workoutRoutineCount;
    private int workoutRoutineSet;
    private int workoutRoutineWeight;
    private int workoutRoutineOrder;

    public WorkoutRoutine toEntity() {
        return WorkoutRoutine.builder()
                .reservationId(reservationId)
                .workoutId(workoutId)
                .workoutRoutineCount(workoutRoutineCount)
                .workoutRoutineSet(workoutRoutineSet)
                .workoutRoutineWeight(workoutRoutineWeight)
                .workoutRoutineOrder(workoutRoutineOrder)
                .build();
    }
}
