package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchHolidayRespDto {
    private int holidayId;
    private String holidayDate;
    private int timeId;
    private String name;
    private int confirm;
}
