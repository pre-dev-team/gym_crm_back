package com.predev.gymcrm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainerReview {
    private int trainerReviewId;
    private int trainerId;
    private int userId;
    private String trainerReviewText;
    private double trainerReviewScore;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private User user; // userId






}
