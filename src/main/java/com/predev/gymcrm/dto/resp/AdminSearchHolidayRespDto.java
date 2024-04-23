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
    private int holidayId;
    private int trainerId;
    private int timeId;
    private int confirm;
    private String trainerName;
    private String holidayDate;
    private LocalDateTime createDate;
}
