package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.MakeReservationFromUserReqDto;
import com.predev.gymcrm.entity.ReservationDate;
import com.predev.gymcrm.repository.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReservationService {

    @Autowired
    ReservationMapper reservationMapper;
    public void saveReservation(MakeReservationFromUserReqDto reqDto) {
        ReservationDate reservationDate = reqDto.toReservationDateEntity();
        reservationMapper.saveReservationDate(reservationDate);
    }
    public void saveReservationDate(MakeReservationFromUserReqDto reqDto) {

    }
}
