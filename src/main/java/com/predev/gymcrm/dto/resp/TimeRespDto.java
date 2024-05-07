package com.predev.gymcrm.dto.resp;
import com.predev.gymcrm.entity.Time;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class
TimeRespDto {
    private int timeId;
    private String timeDuration;

    public Time toEntity() {
        return Time.builder()
                .timeId(timeId)
                .timeDuration(timeDuration)
                .build();
    }
}
