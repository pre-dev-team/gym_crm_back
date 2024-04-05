package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.TimeRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Time {
    private int timeId;
    private String timePeriod;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public TimeRespDto toTimeRespDto() {
        return TimeRespDto.builder()
                .timeId(timeId)
                .timePeriod(timePeriod)
                .build();
    }
}
