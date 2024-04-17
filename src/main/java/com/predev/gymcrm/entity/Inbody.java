package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.InbodyRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inbody {
    private int inbodyId;
    private String inbodyUrl;
    private int weight;
    private int muscleMass;
    private int fatMass;

    public InbodyRespDto toInbodyRespDto() {
        return InbodyRespDto.builder()
                .inbodyId(this.inbodyId)
                .inbodyUrl(this.inbodyUrl)
                .weight(this.weight)
                .muscleMass(this.muscleMass)
                .fatMass(this.fatMass)
                .build();
    }
}