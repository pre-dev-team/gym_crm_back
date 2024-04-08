package com.predev.gymcrm.dto.req;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MyTodayScheduleReqDto {
    private int trainerId;
    private String today;
}
