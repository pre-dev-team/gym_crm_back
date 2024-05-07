package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainerSearchForReservationRespDto {
    private int trainerId;
    private String trainerProfileImgUrl;
    private String name;
}
