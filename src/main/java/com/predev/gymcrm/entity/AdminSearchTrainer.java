package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.AdminSearchTrainerRespDto;
import lombok.Data;

@Data
public class AdminSearchTrainer {
    private int trainerAccountId;
    private int trainerId;
    private String trainerProfileImgUrl;
    private String username;
    private String name;
    private String phone;
    private String email;
    private int memberCount;
    private double avgScore;

    public AdminSearchTrainerRespDto toAdminSearchTrainerRespDto() {
        return AdminSearchTrainerRespDto.builder()
                .trainerId(trainerId)
                .name(name)
                .avgScore(avgScore)
                .memberCount(memberCount)
                .build();
    }
}
