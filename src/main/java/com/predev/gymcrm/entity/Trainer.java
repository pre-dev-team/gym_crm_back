package com.predev.gymcrm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Trainer {
    private int trainer_id;
    private int account_id;
    private String trainerProfileImgUrl;

    private List<Reservation> reservations;
    private List<TrainerReview> trainerReviews;
}
