package com.predev.gymcrm.dto.req;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
public class MyTodayScheduleReqDto {
    private int trainerId;
    private String today;
}
