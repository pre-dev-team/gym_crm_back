package com.predev.gymcrm.dto.req;

import lombok.Data;

@Data
public class TrainerEditPasswordReqDto {
    private String prevPassword;
    private String password;
}
