package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Reservation;
import com.predev.gymcrm.entity.Trainer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    public int saveReservation(Reservation reservation);
    public List<Reservation> getAllReservation();
    public Reservation findReservationByUserId(int userId);
    public List<Reservation> findReservationByDate(
            @Param("userId") int userId,
            @Param("trainerId") int trainerId,
            @Param("reservationDate") String reservationDate
    );
    public List<Trainer> findTrainerByDay(
            @Param("reservationDate") String reservationDate,
            @Param("timeId") int timeId
    );
}