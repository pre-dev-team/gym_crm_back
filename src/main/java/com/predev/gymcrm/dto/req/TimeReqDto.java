package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class TimeReqDto {
    private int timeId;
    private String timePeriod;

    public Time toEntity() {
        return Time.builder()
                .timeId(timeId)
                .timePeriod(timePeriod)
                .build();
    }
}
