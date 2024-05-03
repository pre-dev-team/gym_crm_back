package com.predev.gymcrm.dto.resp;

import com.predev.gymcrm.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchAllReservationRespDto {
    private int reservationId;
    private int userId;
    private String username;
    private String name;
    private int trainerId;
    private String trainerUsername;
    private String trainerName;
    private int timeId;
    private String timeDuration;

}
