package com.predev.gymcrm.dto.req;

import lombok.Data;

@Data
public class SearchReservationUserReqDto {
    private String startDate;
    private String endDate;
    private int accountId;
}
