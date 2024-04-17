package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Inbody;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InbodyReqDto {
    private String inbodyUrl;
    private int weight;
    private int muscleMass;
    private int fatMass;

    public Inbody toInbodyEntity() {
        return Inbody.builder()
                .inbodyUrl(this.inbodyUrl)
                .weight(this.weight)
                .muscleMass(this.muscleMass)
                .fatMass(this.fatMass)
                .build();
    }
}