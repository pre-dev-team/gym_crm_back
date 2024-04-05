package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Reservation;
import com.predev.gymcrm.entity.ReservationDate;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class MakeReservationFromUserReqDto {
    private int userId;
    private int trainerId;
    private int timeId;
    private LocalDateTime date;

    public Reservation toEntity(ReservationDate reservationDate) {
        return Reservation.builder()
                .userId(userId)
                .trainerId(trainerId)
                .timeId(timeId)
                .reservationDate(reservationDate)
                .build();
    }

    public ReservationDate toReservationDateEntity() {
        return ReservationDate.builder()
                .reservationDateName(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
