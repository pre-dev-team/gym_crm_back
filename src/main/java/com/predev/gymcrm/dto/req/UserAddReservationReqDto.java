package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Reservation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAddReservationReqDto {

    private int accountId;
    private int trainerId;
    private int timeId;
    private LocalDateTime date;

    public Reservation toReservationEntity(String date, int userId) {
        return Reservation.builder()
                .userId(userId)
                .trainerId(trainerId)
                .timeId(timeId)
                .reservationDate(date)
                .build();
    }
}
