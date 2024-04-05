package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.ReservationDate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    public int saveReservationDate(ReservationDate reservationDate);
}
