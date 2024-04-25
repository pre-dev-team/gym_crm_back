package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class SearchMyMembersRespDto {
    private int userId;
    private int accountId;
    private String name;
    private String phone;
    private String email;

}
