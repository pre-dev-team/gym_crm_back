package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Inbody;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrainerAddInbodyReqDto {
    private int userId;
    private String inbodyUrl;
    private int weight;
    private int muscleMass;
    private int fatMass;

    public Inbody toInbodyEntity() {
        return Inbody.builder()
                .userId(userId)
                .inbodyUrl(inbodyUrl)
                .weight(weight)
                .muscleMass(muscleMass)
                .fatMass(fatMass)
                .build();
    }
}