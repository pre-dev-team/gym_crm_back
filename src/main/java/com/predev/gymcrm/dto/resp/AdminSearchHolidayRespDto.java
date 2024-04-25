package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AdminSearchHolidayRespDto {
    private int trainerId;
    private String holidayDate;
    private int startTimeId;
    private int endTimeId;
    private String crateDate;
    private String name;
}
