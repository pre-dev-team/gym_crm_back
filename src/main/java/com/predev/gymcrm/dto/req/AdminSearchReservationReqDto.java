package com.predev.gymcrm.dto.req;

import lombok.Data;

@Data
public class AdminSearchReservationReqDto {
    private String startDate;
    private String endDate;
    private int searchType;
    private String name;
}
