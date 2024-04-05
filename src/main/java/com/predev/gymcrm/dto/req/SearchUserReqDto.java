package com.predev.gymcrm.dto.req;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchUserReqDto {
    private int page;
    private int count;
    private int accountId;
}
