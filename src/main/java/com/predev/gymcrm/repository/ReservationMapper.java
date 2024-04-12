package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Reservation;
import com.predev.gymcrm.entity.Trainer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    int saveReservation(Reservation reservation);
    List<Reservation> getAllReservation();
    List<Reservation> findReservationsByUserId(int userId);
    List<Reservation> findReservationByDate(
            @Param("userId") int userId,
            @Param("trainerId") int trainerId,
            @Param("reservationDate") String reservationDate
    );
    List<Trainer> findTrainerByDay(
            @Param("reservationDate") String reservationDate,
            @Param("timeId") int timeId
    );
    List<Reservation> getTodayReservation(
            @Param("trainerId") int trainerId,
            @Param("today") String today
    );

    int findReservationCountByUserId(int userId);
}