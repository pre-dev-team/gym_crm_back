package com.predev.gymcrm.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFindPasswordReqDto {
    private String username;
    private String email;
}
