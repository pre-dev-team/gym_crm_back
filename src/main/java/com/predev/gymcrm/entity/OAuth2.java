package com.predev.gymcrm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OAuth2 {
    private int oauth2Id;
    private String oauth2Name;
    private int accountId;
    private String oauth2ProviderName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
