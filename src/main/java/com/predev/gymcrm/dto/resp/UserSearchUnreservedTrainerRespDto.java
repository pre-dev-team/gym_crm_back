package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSearchUnreservedTrainerRespDto {
    private int trainerId;
    private String name;
    private String trainerProfileImgUrl;
}
