package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AdminSearchTrainerRespDto {
    private int trainerId;
    private String name;
    private int memberCount;
    private float avgScore;
}
