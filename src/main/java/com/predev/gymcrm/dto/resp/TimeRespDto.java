package com.predev.gymcrm.dto.resp;
import com.predev.gymcrm.entity.Time;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class
TimeRespDto {
    private int timeId;         // 시간대 ID
    private String timeDuration;  // 시간대

    public Time toEntity() {
        return Time.builder()
                .timeId(timeId)
                .timeDuration(timeDuration)
                .build();
    }
}
