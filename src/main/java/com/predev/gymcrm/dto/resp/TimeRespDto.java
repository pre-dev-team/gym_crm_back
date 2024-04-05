package com.predev.gymcrm.dto.resp;
import com.predev.gymcrm.entity.Time;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class
TimeRespDto {
    private int timeId;         // 시간대 ID
    private String timePeriod;  // 시간대

    public Time toEntity() {
        return Time.builder()
                .timeId(timeId)
                .timePeriod(timePeriod)
                .build();
    }
}
