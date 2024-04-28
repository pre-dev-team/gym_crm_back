package com.predev.gymcrm.dto.resp;

import com.predev.gymcrm.entity.Time;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchReservationUserRespDto {
    private int reservationId;
    private int userId;
    private String name;
    private int timeId;
    private String timeDuration;
    private String reservationDate;
}
