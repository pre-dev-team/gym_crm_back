package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Holiday;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrainerAddHolidayReqDto {
    private int accountId;
    private String holidayDate;
    private int startTimeId;
    private int endTimeId;
    private int confirm;

    public Holiday toTrainerHolidayEntity(String date, int trainerId) {
        return Holiday.builder()
                .trainerId(trainerId)
                .holidayDate(date)
                .confirm(confirm)
                .build();
    }
}
