package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Trainer;
import lombok.Data;

@Data
public class UpdateTrainerProfileImgReqDto {
    private int accountId;
    private String trainerProfileImgUrl;

    public Trainer toEntity() {
        return Trainer.builder()
                .accountId(accountId)
                .trainerProfileImgUrl(trainerProfileImgUrl)
                .build();
    }
}
