package com.predev.gymcrm.dto.resp;

import java.time.LocalDateTime;

public class TrainerReviewRespDto {
    private int trainerReviewId;
    private int trainerId;
    private String trainerName;
    private int userId;
    private String userName;
    private String trainerReviewText;
    private Double trainerReviewScore;
    private String trainerProfileImgUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
