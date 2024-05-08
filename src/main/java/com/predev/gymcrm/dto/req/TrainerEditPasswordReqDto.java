package com.predev.gymcrm.dto.req;

import lombok.Data;

@Data
public class TrainerEditPasswordReqDto {
    private int accountId;
    private String prevPassword;
    private String password;
    private String checkPassword;
}
