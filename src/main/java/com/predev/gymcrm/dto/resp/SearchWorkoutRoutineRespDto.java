package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchWorkoutRoutineRespDto {
    private int workoutRoutineId;
    private int reservationId;
    private int workoutId;
    private String workoutName;
    private int workoutCategoryId;
    private String workoutCategoryName;
    private int workoutRoutineCount;
    private int workoutRoutineSet;
    private int workoutRoutineWeight;
    private int workoutRoutineOrder;
}
