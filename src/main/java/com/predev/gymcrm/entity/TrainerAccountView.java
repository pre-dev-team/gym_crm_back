package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.UserSearchUnreservedTrainerRespDto;
import com.predev.gymcrm.dto.resp.TrainerSearchForReservationRespDto;
import com.predev.gymcrm.dto.resp.TrainerSearchInfoRespDto;
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

    public TrainerSearchInfoRespDto toTrainerInfoRespDto() {
        return TrainerSearchInfoRespDto.builder()
                .trainerId(trainerId)
                .name(name)
                .username(username)
                .phone(phone)
                .email(email)
                .trainerProfileImgUrl(trainerProfileImgUrl)
                .build();
    }

    public TrainerSearchForReservationRespDto trainerForReservationRespDto() {
        return TrainerSearchForReservationRespDto.builder()
                .trainerId(trainerId)
                .trainerProfileImgUrl(trainerProfileImgUrl)
                .name(name)
                .build();
    }

    public UserSearchUnreservedTrainerRespDto toSearchUnreservedTrainerRespDto() {
        return UserSearchUnreservedTrainerRespDto.builder()
                .trainerId(trainerId)
                .trainerProfileImgUrl(trainerProfileImgUrl)
                .name(name)
                .build();
    }
}
