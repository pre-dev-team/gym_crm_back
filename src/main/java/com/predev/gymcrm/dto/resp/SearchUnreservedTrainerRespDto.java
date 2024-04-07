package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchUnreservedTrainerRespDto {
    private int trinerId;
    private String name;
    private String trainerProfileImgUrl;
}
