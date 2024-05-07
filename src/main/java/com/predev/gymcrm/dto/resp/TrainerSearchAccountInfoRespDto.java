package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainerSearchAccountInfoRespDto {
    private int accountId;
    private String username;
    private String name;
    private String phone;
    private String email;
}
