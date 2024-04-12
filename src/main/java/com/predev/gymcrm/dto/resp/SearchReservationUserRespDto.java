package com.predev.gymcrm.dto.resp;

import com.predev.gymcrm.entity.Time;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchReservationUserRespDto {
    private int UserId;
    private String name;
    private String timeDuration;
}
