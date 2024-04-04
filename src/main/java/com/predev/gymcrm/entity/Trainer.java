package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.req.SearchTrainerReqDto;
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
    private String trainerUserName;
    private String trainerName;
    private String trainerPassword;
    private String trainerEmail;
    private String trainerPhone;
    private String trainerProfileImgUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public SearchTrainerReqDto toSearchTrainerReqDto() {
        return SearchTrainerReqDto.builder()
                .trainerId(trainerId)
                .build();
    }
}
