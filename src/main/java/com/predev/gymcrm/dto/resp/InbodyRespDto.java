package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class InbodyRespDto {
    private int inbodyId;
    private String inbodyUrl;
    private int weight;
    private int muscleMass;
    private int fatMass;
    private String createDate;
}
