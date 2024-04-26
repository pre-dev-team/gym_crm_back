package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.SearchMyMembersRespDto;
import com.predev.gymcrm.dto.resp.SearchReservationRespDto;
import com.predev.gymcrm.dto.resp.SearchReservationUserRespDto;
import com.predev.gymcrm.dto.resp.SelectMyMembersInformationRespDto;
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

    public SearchReservationRespDto toSearchReservationRespDto() {
        return SearchReservationRespDto.builder()
                .reservationId(reservationId)
                .userId(userId)
                .trainerId(trainerId)
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
                .build();
    }

}
