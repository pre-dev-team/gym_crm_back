package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.AdminSearchWeeklyTrainerReservationCountsRespDto;
import lombok.Data;

@Data
public class WeeklyTrainerReservationCounts {
    private int trainerId;
    private String trainerName;
    private int firstWeekCount;
    private int secondWeekCount;
    private int thirdWeekCount;
    private int forthWeekCount;

    public AdminSearchWeeklyTrainerReservationCountsRespDto toRespDto() {
        return AdminSearchWeeklyTrainerReservationCountsRespDto.builder()
                .trainerId(trainerId)
                .trainerName(trainerName)
                .firstWeekCount(firstWeekCount)
                .secondWeekCount(secondWeekCount)
                .thirdWeekCount(thirdWeekCount)
                .forthWeekCount(forthWeekCount)
                .build();
    }
}
