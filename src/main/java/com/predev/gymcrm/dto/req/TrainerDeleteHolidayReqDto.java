package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Holiday;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrainerDeleteHolidayReqDto {
    private int accountId;
    private String holidayDate;

    public Holiday toDeleteHolidayEntity(int trainerId) {
        return Holiday.builder()
                .trainerId(trainerId)
                .holidayDate(holidayDate)
                .build();
    }
}
