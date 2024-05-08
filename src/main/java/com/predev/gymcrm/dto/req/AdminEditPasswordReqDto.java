package com.predev.gymcrm.dto.req;

import lombok.Data;

@Data
public class AdminEditPasswordReqDto {
    private String prevPassword;
    private String Password;
    private String checkPassword;
}
