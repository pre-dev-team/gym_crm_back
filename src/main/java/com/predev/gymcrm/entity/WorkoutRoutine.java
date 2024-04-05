package com.predev.gymcrm.entity;

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
}
