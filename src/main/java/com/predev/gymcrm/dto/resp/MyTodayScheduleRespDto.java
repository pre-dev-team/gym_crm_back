package com.predev.gymcrm.dto.resp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MyTodayScheduleRespDto {
    private int reservationId;
    private int userId;
    private String name;
    private String phone;
    private int timeId;
    private String timeDuration;


}
