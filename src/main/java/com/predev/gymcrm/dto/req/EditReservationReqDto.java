package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Reservation;
import lombok.Data;


@Data
public class EditReservationReqDto {

    private int prevReservationId;
    private int accountId;
    private int trainerId;
    private int timeId;
    private String date;
    public Reservation toReservationEntity(String date, int userId) {
        return Reservation.builder()
                .userId(userId)
                .trainerId(trainerId)
                .timeId(timeId)
                .reservationDate(date)
                .build();
    }
}
