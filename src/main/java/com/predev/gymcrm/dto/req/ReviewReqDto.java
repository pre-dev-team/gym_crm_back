package com.predev.gymcrm.dto.req;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewReqDto {
    private int trainerId;
    private int userId;
    private String reviewText;
    private int reviewScore;
}
