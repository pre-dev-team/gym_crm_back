package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.MakeReservationReqDto;
import com.predev.gymcrm.dto.req.SearchDayReservationReqDto;
import com.predev.gymcrm.dto.resp.SearchReservationRespDto;
import com.predev.gymcrm.entity.Reservation;

import com.predev.gymcrm.entity.Time;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private AuthMapper authMapper;

    public List<SearchReservationRespDto> findAll() {
        return reservationMapper.getAllReservation().stream()
                .map(Reservation::toSearchReservationRespDto)
                .map(respDto -> {
                    respDto.setUsername(authMapper.findAccountByUserId(respDto.getUserId()).getUsername());
                    respDto.setName(authMapper.findAccountByUserId(respDto.getUserId()).getName());
                    respDto.setTrainerUsername(authMapper.findAccountByTrainerId(respDto.getTrainerId()).getUsername());
                    respDto.setTrainerName(authMapper.findAccountByTrainerId(respDto.getTrainerId()).getName());
                    return respDto;
                })
                .collect(Collectors.toList());
    }

    public void insertReservation(MakeReservationReqDto reqDto) {
        String date = reqDto.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        reservationMapper.saveReservation(reqDto.toReservationEntity(date));
    }

    public List<Time> SearchDayReservation(SearchDayReservationReqDto reqDto) {
        String date = LocalDateTime.parse(reqDto.getDate(), DateTimeFormatter.ISO_DATE_TIME).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Reservation> reservations = reservationMapper.findReservationByDate(reqDto.getUserId(), reqDto.getTrainerId(), date);
        return reservations.stream().map(Reservation::getTime).collect(Collectors.toList());
    }

//    public SearchReservationRespDto findReservationByUserId(int userId) {
//        Reservation reservation = reservationMapper.findReservationByUserId(userId);
//
//        SearchReservationRespDto searchReservationRespDto =
//                SearchReservationRespDto.builder()
//                        .reservationId(reservation.getReservationId())
//                        .userId(reservation.getUserId())
//                        .username(reservation.getUserUserName())
//                        .trainerId(reservation.getTrainerId())
//                        .trainerName(reservation.getTrainerUserName())
//                        .timeId(reservation.getTimeId())
//                        .timeName(reservation.getTimePeriod())
//                        .reservationDateId(reservation.getReservationDateId())
//                        .reservationDateName(reservation.getReservationDateName())
//                        .build();
//        return searchReservationRespDto;
//    }

}
