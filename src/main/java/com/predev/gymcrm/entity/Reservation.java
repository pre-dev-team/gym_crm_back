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

    public SearchReservationUserRespDto toSearchReservationUserRespDto() {
        return SearchReservationUserRespDto.builder()
                .reservationId(reservationId)
                .reservationDate(reservationDate)
                .userId(userId)
                .name(userAccountView.getName())
                .timeId(timeId)
                .timeDuration(time.getTimeDuration())
                .build();
    }

    public SearchMyMembersRespDto toSearchMyMembersRespDto() {
        return SearchMyMembersRespDto.builder()
                .userId(userId)
                .accountId(userAccountView.getAccountId())
                .name(userAccountView.getName())
                .email(userAccountView.getEmail())
                .phone(userAccountView.getPhone())
                .build();
    }

    public SelectMyMembersInformationRespDto toSelectMyMembersInformationRespDto() {
        return SelectMyMembersInformationRespDto.builder()
                .accountId(trainerAccountView.getAccountId())
                .userId(userId)
                .reservationDate(reservationDate)
                .name(userAccountView.getName())
                .phone(userAccountView.getPhone())
                .timeDuration(time.getTimeDuration())
                .build();
    }

    public MyTodayScheduleRespDto toMyTodayScheduleRespDto() {
        return MyTodayScheduleRespDto.builder()
                .reservationId(reservationId)
                .trainerId(trainerId)
                .timeId(timeId)
                .timeDuration(time.getTimeDuration())
                .phone(userAccountView.getPhone())
                .name(userAccountView.getName())
                .build();
    }

    public SearchReservationRespDto toSearchReservationRespDto() {
        return SearchReservationRespDto.builder()
                .reservationId(reservationId)
                .reservationDate(reservationDate)
                .trainerId(trainerId)
                .name(trainerAccountView.getName())
                .timeId(timeId)
                .timeDuration(time.getTimeDuration())
                .build();
    }

    public SearchAllReservationRespDto toSearchAllReservationRespDto() {
        return SearchAllReservationRespDto.builder()
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
