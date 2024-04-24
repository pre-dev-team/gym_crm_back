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
    private int oAuth2Id;
    private String oAuth2Name;
    private int accountId;
    private String oAuth2ProviderName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
