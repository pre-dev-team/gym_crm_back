package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.SearchReservationRespDto;
import com.predev.gymcrm.dto.resp.SearchReservationUserRespDto;
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
public class Reservation {
    private int reservationId;
    private int userId;
    private int trainerId;
    private int timeId;
    private String reservationDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Time time;
    private User user;
    private Trainer trainer;
    private List<WorkoutRoutine> workoutRoutines;

    public SearchReservationRespDto toSearchReservationRespDto() {
        return SearchReservationRespDto.builder()
                .reservationId(reservationId)
                .userId(userId)
                .trainerId(trainerId)
                .timeId(timeId)
                .timeDuration(time.getTimeDuration())
                .build();
    }

}
