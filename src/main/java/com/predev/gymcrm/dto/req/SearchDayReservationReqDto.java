package com.predev.gymcrm.dto.req;

import lombok.Data;


@Data
public class SearchDayReservationReqDto {
    private String date;
    private String useId;
    private String trainerId;
}
