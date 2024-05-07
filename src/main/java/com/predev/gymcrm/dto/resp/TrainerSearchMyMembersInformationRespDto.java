package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerSearchMyMembersInformationRespDto {
    private int accountId;
    private int userId;
    private String reservationDate;
    private String name;
    private String phone;
    private String timeDuration;
}
