package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerInfoRespDto {
    private int trainerId;
    private String name;
    private String username;
    private String phone;
    private String email;
    private String trainerProfileImgUrl;

}