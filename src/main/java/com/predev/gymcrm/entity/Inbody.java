package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.InbodyRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inbody {
    private int inbodyId;
    private String inbodyUrl;
    private int userId;
    private int weight;
    private int muscleMass;
    private int fatMass;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public InbodyRespDto toInbodyRespDto() {
        return InbodyRespDto.builder()
                .inbodyId(inbodyId)
                .inbodyUrl(inbodyUrl)
                .weight(weight)
                .muscleMass(muscleMass)
                .fatMass(fatMass)
                .createDate(createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}