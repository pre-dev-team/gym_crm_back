package com.predev.gymcrm.dto.resp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminSearchReservationRespDto {
    private int reservationId;
    private String reservationDate;
    private int trainerId;
    private String trainerName;
    private int userId;
    private String userName;
    private int userAccountId;
    private int timeId;
    private String timeDuration;
}
