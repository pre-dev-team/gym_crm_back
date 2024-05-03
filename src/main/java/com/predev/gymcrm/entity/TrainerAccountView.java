package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.SearchUnreservedTrainerRespDto;
import com.predev.gymcrm.dto.resp.TrainerForReservationRespDto;
import com.predev.gymcrm.dto.resp.TrainerInfoRespDto;
import lombok.Data;

@Data
public class TrainerAccountView {
    private int accountId;
    private int trainerId;
    private String trainerProfileImgUrl;
    private int roleId;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;

    public TrainerInfoRespDto toTrainerInfoRespDto() {
        return TrainerInfoRespDto.builder()
                .trainerId(trainerId)
                .name(name)
                .username(username)
                .phone(phone)
                .email(email)
                .trainerProfileImgUrl(trainerProfileImgUrl)
                .build();
    }

    public TrainerForReservationRespDto trainerForReservationRespDto() {
        return TrainerForReservationRespDto.builder()
                .trainerId(trainerId)
                .trainerProfileImgUrl(trainerProfileImgUrl)
                .name(name)
                .build();
    }

    public SearchUnreservedTrainerRespDto toSearchUnreservedTrainerRespDto() {
        return SearchUnreservedTrainerRespDto.builder()
                .trainerId(trainerId)
                .trainerProfileImgUrl(trainerProfileImgUrl)
                .name(name)
                .build();
    }
}
