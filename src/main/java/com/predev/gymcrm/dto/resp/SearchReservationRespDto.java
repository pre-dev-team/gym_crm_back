package com.predev.gymcrm.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class SearchReservationRespDto {
    private int reservationId;
    private int trainerId;
    private String reservationDate;
    private int timeId;
    private String timeDuration;
    private String name;
}
