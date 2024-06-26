package com.predev.gymcrm.dto.resp;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SearchReviewRespDto {
    private int trainerReviewId;
    private int trainerId;
    private int userId;
    private String reviewText;
    private int reviewScore;
    private String trainerName;
    private String trainerProfileImgUrl;
    private LocalDateTime createDate;
}
