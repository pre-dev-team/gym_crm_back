package com.predev.gymcrm.dto.req;

import lombok.Data;

@Data
public class OAuth2MergeReqDto {
    private String username;
    private String password;
    private String oauth2Name;
    private String oauth2ProviderName;
}
