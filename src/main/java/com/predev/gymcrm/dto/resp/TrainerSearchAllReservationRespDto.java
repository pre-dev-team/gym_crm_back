package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerSearchAllReservationRespDto {
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
