package com.predev.gymcrm.dto.req;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminSearchReservationCountReqDto {
    private String startDate;
    private String endDate;
}
