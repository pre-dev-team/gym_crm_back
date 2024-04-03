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
public class WorkoutCategory {
    private int workoutCategoryId;
    private String workoutCategoryName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
