package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectHolidayRespDto {
    private int holidayId;
    private String holidayDate;
    private int TimeId;
    private String name;
    private int confirm;
}
