package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    public int saveReservation(Reservation reservation);
    public List<Reservation> getAllReservation();
    public Reservation findReservationByUserId(int userId);
    public Reservation findReservationByDate(String Date);
}