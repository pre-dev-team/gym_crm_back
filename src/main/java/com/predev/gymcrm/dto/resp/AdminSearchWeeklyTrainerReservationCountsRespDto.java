package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AdminSearchWeeklyTrainerReservationCountsRespDto {
    private int trainerId;
    private String trainerName;
    private int firstWeekCount;
    private int secondWeekCount;
    private int thirdWeekCount;
    private int forthWeekCount;
}
