package com.predev.gymcrm.dto.req;

import lombok.Data;

@Data
public class AdminDecideHolidayAppliesReqDto {
    private int trainerId;
    private String holidayDate;
    private boolean status;
}
