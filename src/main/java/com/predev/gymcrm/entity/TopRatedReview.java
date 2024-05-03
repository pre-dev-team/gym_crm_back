package com.predev.gymcrm.entity;


import com.predev.gymcrm.dto.resp.TopRatedReviewsRespDto;
import lombok.Data;

@Data
public class TopRatedReview {
    private int rankNumber;
    private int trainerReviewId;
    private int userId;
    private String username;
    private int trainerReviewScore;
    private String trainerReviewText;
    private String trainerName;
    private String trainerProfileImgUrl;
    private int reviewCount;

    public TopRatedReviewsRespDto toRespDto() {
        return TopRatedReviewsRespDto.builder()
                .rankNumber(rankNumber)
                .trainerReviewId(trainerReviewId)
                .userId(userId)
                .username(username)
                .trainerReviewScore(trainerReviewScore)
                .trainerReviewText(trainerReviewText)
                .trainerName(trainerName)
                .trainerProfileImgUrl(trainerProfileImgUrl)
                .reviewCount(reviewCount)
                .build();
    }
}
