package com.predev.gymcrm.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class SearchReservationRespDto {
    private int reservationId;
    private int userId;
    private String username;
    private int trainerId;
    private String trainerName;
    private int timeId;
    private String timeName;
    private int reservationDateId;
    private String reservationDateName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
