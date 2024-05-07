package com.predev.gymcrm.dto.resp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrainerSearchMyMembersRespDto {
    private int userId;
    private int accountId;
    private String name;
    private String phone;
    private String email;

}
