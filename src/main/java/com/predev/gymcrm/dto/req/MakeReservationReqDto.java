package com.predev.gymcrm.dto.req;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MakeReservationReqDto {

    private int userId;
    private int trainerId;
    private int timeId;
    private LocalDateTime date;
}
