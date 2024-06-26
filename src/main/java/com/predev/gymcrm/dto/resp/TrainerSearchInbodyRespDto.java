package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerSearchInbodyRespDto {
    private int inbodyId;
    private String inbodyUrl;
    private int userId;
    private int weight;
    private String name;
    private int muscleMass;
    private int fatMass;
}
