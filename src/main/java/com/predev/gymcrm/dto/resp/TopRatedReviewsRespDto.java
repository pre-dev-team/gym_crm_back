package com.predev.gymcrm.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TopRatedReviewsRespDto {
    private int rankNumber;
    private int trainerReviewId;
    private int userId;
    private String username;
    private int trainerReviewScore;
    private String trainerReviewText;
    private String trainerName;
    private String trainerProfileImgUrl;
    private int reviewCount;
}
