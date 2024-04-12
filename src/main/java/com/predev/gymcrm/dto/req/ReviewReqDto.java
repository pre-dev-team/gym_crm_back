package com.predev.gymcrm.dto.req;
import com.predev.gymcrm.entity.TrainerReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewReqDto {
    private int trainerId;
    private int accountId;
    private String reviewText;
    private int reviewScore;

    public TrainerReview toEntity() {
        return TrainerReview.builder()
                .userId(0)
                .trainerId(trainerId)
                .trainerReviewText(reviewText)
                .trainerReviewScore(reviewScore)
                .build();
    }
}
