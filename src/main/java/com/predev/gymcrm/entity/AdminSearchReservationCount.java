package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.AdminSearchReservationCountRespDto;
import lombok.Data;

@Data
public class AdminSearchReservationCount {
    private int trainerId;
    private String trainerName;
    private int reservationCount;

    public AdminSearchReservationCountRespDto toRespDto() {
        return AdminSearchReservationCountRespDto.builder()
                .trainerId(trainerId)
                .trainerName(trainerName)
                .reservationCount(reservationCount)
                .build();
    }
}
