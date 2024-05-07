package com.predev.gymcrm.dto.resp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminSearchReservationUserRespDto {
    private int reservationId;
    private int userId;
    private String name;
    private int timeId;
    private String timeDuration;
    private String reservationDate;
}
