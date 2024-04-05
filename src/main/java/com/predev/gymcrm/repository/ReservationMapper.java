package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    public List<Reservation> getAllReservation();
    public Reservation findReservationByUserId(int userId);
    public int saveReservationDate(ReservationDate reservationDate);


}