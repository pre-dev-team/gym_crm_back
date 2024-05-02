package com.predev.gymcrm.dto.req;

import lombok.Data;

@Data
public class AdminPasswordChangeReqDto {
    private String prevPassword;
    private String newPassword;
}
