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
public class Reservation {
    private int reservationId;
    private int userId;
    private int trainerId;
    private int timeId;
    private int reservationDateId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private ReservationDate reservationDate;
    private Time time;
}
