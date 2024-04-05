package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.resp.SearchReservationRespDto;
import com.predev.gymcrm.entity.Reservation;

import com.predev.gymcrm.dto.req.MakeReservationFromUserReqDto;
import com.predev.gymcrm.entity.ReservationDate;
import com.predev.gymcrm.repository.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReservationService {

    @Autowired

    private ReservationMapper reservationMapper;

    public List<SearchReservationRespDto> findAll() {
        List<SearchReservationRespDto> respDtoList = new ArrayList<>();
        List<Reservation> reservationList = reservationMapper.getAllReservation();
        for(Reservation reservation : reservationList) {
            respDtoList.add(reservation.toSearchReservationRespDto());
        }
        return respDtoList;
    }

    public SearchReservationRespDto findReservationByUserId(int userId) {
        Reservation reservation = reservationMapper.findReservationByUserId(userId);

        SearchReservationRespDto searchReservationRespDto =
                SearchReservationRespDto.builder()
                        .reservationId(reservation.getReservationId())
                        .userId(reservation.getUserId())
                        .username(reservation.getUserUserName())
                        .trainerId(reservation.getTrainerId())
                        .trainerName(reservation.getTrainerUserName())
                        .timeId(reservation.getTimeId())
                        .timeName(reservation.getTimePeriod())
                        .reservationDateId(reservation.getReservationDateId())
                        .reservationDateName(reservation.getReservationDateName())
                        .build();
        return searchReservationRespDto;
    }


    public void saveReservation(MakeReservationFromUserReqDto reqDto) {
        ReservationDate reservationDate = reqDto.toReservationDateEntity();
        reservationMapper.saveReservationDate(reservationDate);
    }
  
    public void saveReservationDate(MakeReservationFromUserReqDto reqDto) {

    }
}
