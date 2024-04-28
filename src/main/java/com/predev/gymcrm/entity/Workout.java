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
public class Workout {
    private int workoutId;
    private int workoutCategoryId;
    private String workoutName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private WorkoutCategory workoutCategory;
}
