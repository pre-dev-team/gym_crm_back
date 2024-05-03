package com.predev.gymcrm.dto.req;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrainerSearchTodayScheduleReqDto {
    private int trainerId;
    private String today;
}
