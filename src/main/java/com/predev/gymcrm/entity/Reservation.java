package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.SearchReservationRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    private int reservationId;
    private int userId;
    private String userUserName;
    private int trainerId;
    private String trainerUserName;
    private int timeId;
    private String timePeriod;
    private int reservationDateId;
    private String reservationDateName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private ReservationDate reservationDate;
    private Time time;
    private User user;
    private Trainer trainer;

    public SearchReservationRespDto toSearchReservationRespDto() {
        return SearchReservationRespDto.builder()
                .reservationId(reservationId)
                .userId(userId)
                .username(user.getUserUsername())
                .trainerId(trainerId)
                .trainerName(trainer.getTrainerUserName())
                .timeId(timeId)
                .timeName(time.getTimePeriod())
                .reservationDateId(reservationDateId)
                .reservationDateName(reservationDate.getReservationDateName())
                .build();
    }
}
