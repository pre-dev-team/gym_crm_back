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
public class ReservationDate {
    private int reservationDateId;
    private String reservationDateName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
