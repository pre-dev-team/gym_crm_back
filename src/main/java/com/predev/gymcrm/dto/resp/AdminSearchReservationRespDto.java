package com.predev.gymcrm.dto.resp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminSearchReservationRespDto {
    private int reservationId;
    private int trainerId;
    private String reservationDate;
    private int timeId;
    private String timeDuration;
    private String name;
}
