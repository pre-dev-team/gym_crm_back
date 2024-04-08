package com.predev.gymcrm.dto.req;

import lombok.Data;


@Data
public class SearchDayReservationReqDto {
    private int accountId;
    private int trainerId;
    private String date;
}
