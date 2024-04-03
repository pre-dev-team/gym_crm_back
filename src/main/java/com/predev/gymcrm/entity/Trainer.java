package com.predev.gymcrm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Trainer {
    private int trainerId;
    private int roleId;
    private String trainerUserName;
    private String trainerName;
    private String trainerPassword;
    private String trainerEmail;
    private String trainerPhone;
    private String trainerProfileImgUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
