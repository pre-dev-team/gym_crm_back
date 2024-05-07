package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.*;
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
    private User user;
    private Trainer trainer;
    private List<WorkoutRoutine> workoutRoutines;
    private UserAccountView userAccountView;
    private TrainerAccountView trainerAccountView;

    public AdminSearchReservationUserRespDto toSearchReservationUserRespDto() {
        return AdminSearchReservationUserRespDto.builder()
                .reservationId(reservationId)
                .reservationDate(reservationDate)
                .userId(userId)
                .name(userAccountView.getName())
                .timeId(timeId)
                .timeDuration(time.getTimeDuration())
                .build();
    }

    public TrainerSearchMyMembersRespDto toSearchMyMembersRespDto() {
        return TrainerSearchMyMembersRespDto.builder()
                .userId(userId)
                .accountId(userAccountView.getAccountId())
                .name(userAccountView.getName())
                .email(userAccountView.getEmail())
                .phone(userAccountView.getPhone())
                .build();
    }

    public TrainerSearchMyMembersInformationRespDto toSelectMyMembersInformationRespDto() {
        return TrainerSearchMyMembersInformationRespDto.builder()
                .accountId(trainerAccountView.getAccountId())
                .userId(userId)
                .reservationDate(reservationDate)
                .name(userAccountView.getName())
                .phone(userAccountView.getPhone())
                .timeDuration(time.getTimeDuration())
                .build();
    }

    public TrainerSearchScheduleRespDto toMyTodayScheduleRespDto() {
        return TrainerSearchScheduleRespDto.builder()
                .reservationId(reservationId)
                .trainerId(trainerId)
                .timeId(timeId)
                .timeDuration(time.getTimeDuration())
                .phone(userAccountView.getPhone())
                .name(userAccountView.getName())
                .build();
    }

    public AdminSearchReservationRespDto toSearchReservationRespDto() {
        return AdminSearchReservationRespDto.builder()
                .reservationId(reservationId)
                .reservationDate(reservationDate)
                .trainerId(trainerId)
                .trainerName(trainerAccountView.getName())
                .userId(userId)
                .userName(userAccountView.getName())
                .timeId(timeId)
                .timeDuration(time.getTimeDuration())
                .build();
    }

    public TrainerSearchAllReservationRespDto toSearchAllReservationRespDto() {
        return TrainerSearchAllReservationRespDto.builder()
                .reservationId(reservationId)
                .userId(userId)
                .username(userAccountView.getUsername())
                .name(userAccountView.getName())
                .trainerId(trainerId)
                .trainerUsername(trainerAccountView.getUsername())
                .trainerName(trainerAccountView.getName())
                .timeId(time.getTimeId())
                .timeDuration(time.getTimeDuration())
                .build();
    }
}
