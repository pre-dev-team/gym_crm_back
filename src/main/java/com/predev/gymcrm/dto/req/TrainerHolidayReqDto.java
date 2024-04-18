package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Holiday;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class TrainerHolidayReqDto {
    private int accountId;
    private String holidayDate;
    private int startTimeId;
    private int endTimeId;
    private int confirm;

    public Holiday toTrainerHolidayEntity(String date, int trainerId) {
        return Holiday.builder()
                .trainerId(trainerId)
                .holidayDate(date)
                .timeId(endTimeId)
                .confirm(confirm)
                .build();
    }
}
