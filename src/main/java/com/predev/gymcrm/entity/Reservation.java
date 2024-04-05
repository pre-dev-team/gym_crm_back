package com.predev.gymcrm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    private int reservationId;
    private int userId;
    private int trainerId;
    private int timeId;
    private String reservationDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Time time;
    private Account user;
    private Trainer trainer;
    private List<WorkoutRoutine> workoutRoutines;
}
