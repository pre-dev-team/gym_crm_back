package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.ReviewRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainerReview {
    private int trainerReviewId;
    private int trainerId;
    private int userId;
    private String trainerReviewText;
    private int trainerReviewScore;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Trainer trainer;
    private TrainerAccountView trainerAccountView;
    private UserAccountView userAccountView;
    public ReviewRespDto toReviewRespDto() {
        return ReviewRespDto.builder()
                .trainerReviewId(this.trainerReviewId)
                .trainerId(this.trainerId)
                .userId(this.userId)
                .reviewText(this.trainerReviewText)
                .reviewScore(this.trainerReviewScore)
                .createDate(this.createDate)
                .trainerProfileImgUrl(trainerAccountView.getTrainerProfileImgUrl())
                .trainerName(trainerAccountView.getName())
                .build();
    }
}
