package com.predev.gymcrm.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class SearchReservationRespDto {
    private int reservationId;
    private int userId;
    private int trainerId;
    private String reservationDate;
    private int timeId;
    private String timeDuration;
    private String username;
    private String name;
    private String trainerUsername;
    private String trainerName;
}
