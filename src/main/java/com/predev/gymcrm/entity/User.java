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
public class User {

    private int userId;
    private int accountId;
    private int inbodyId;

    private List<Reservation> reservations;
    private List<TrainerReview> trainerReviews;
}
