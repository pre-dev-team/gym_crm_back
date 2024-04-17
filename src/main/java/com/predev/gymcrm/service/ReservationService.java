package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.*;
import com.predev.gymcrm.dto.resp.*;
import com.predev.gymcrm.entity.*;

import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private AuthMapper authMapper;


    public String trimDateString(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public List<SearchReservationRespDto> findAll() {
        return reservationMapper.getAllReservation().stream()
                .map(Reservation::toSearchReservationRespDto)
                .map(respDto -> {
                    Account userAccount = authMapper.findAccountByUserId(respDto.getUserId());
                    Account trainerAccount = authMapper.findAccountByTrainerId(respDto.getTrainerId());
                    respDto.setUsername(userAccount.getUsername());
                    respDto.setName(userAccount.getName());
                    respDto.setTrainerUsername(trainerAccount.getUsername());
                    respDto.setTrainerName(trainerAccount.getName());
                    return respDto;
                })
                .collect(Collectors.toList());
    }

    public void insertReservation(MakeReservationReqDto reqDto) {
        int userId = authMapper.findUserIdByAccountId(reqDto.getAccountId());
        String date = reqDto.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        reservationMapper.saveReservation(reqDto.toReservationEntity(date, userId));
    }

    public List<SearchReservationRespDto> searchReservationsByUserId(int accountId) {
        int userId = authMapper.findUserIdByAccountId(accountId);
        List<Reservation> reservations =reservationMapper.findReservationsByUserId(userId);

        return reservations.stream().map(reservation -> {
            return SearchReservationRespDto.builder()
                    .reservationDate(reservation.getReservationDate())
                    .trainerId(reservation.getTrainerId())
                    .name(reservation.getTrainer().getAccount().getName())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<Time> SearchDayReservation(SearchDayReservationReqDto reqDto) {
        int userId = 0;
        try{
            userId = authMapper.findUserIdByAccountId(reqDto.getAccountId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<Reservation> reservations = reservationMapper.findReservationByDate(userId, reqDto.getTrainerId(), CommonService.trimDateString(reqDto.getDate()));
        return reservations.stream().map(Reservation::getTime).collect(Collectors.toList());
    }

    public List<SearchUnreservedTrainerRespDto> SearchUnreservedTrainers(SearchUnreservedTrainerReqDto reqDto) {
        List<Trainer> trainers = reservationMapper.findTrainerByDay(CommonService.trimDateString(reqDto.getDate()), reqDto.getTimeId());
        List<SearchUnreservedTrainerRespDto> respDtos = trainers.stream().map(trainer ->
            SearchUnreservedTrainerRespDto.builder()
                    .trainerId(trainer.getTrainerId())
                    .trainerProfileImgUrl(trainer.getTrainerProfileImgUrl())
                    .name(trainer.getAccount().getName())
                    .build()
        ).collect(Collectors.toList());
        return respDtos;
    }

    public List<MyTodayScheduleRespDto> getTodayReservation(MyTodayScheduleReqDto reqDto) {
        String today = CommonService.trimDateString(reqDto.getToday());
        // 예약 정보를 가져오는 비지니스 로직 작성
        // 예시로 비지니스 로직을 호출하여 예약 정보를 가져오는 코드 작성
        List<Reservation> reservations = reservationMapper.getTodayReservation(reqDto.getTrainerId(), today);
        List<MyTodayScheduleRespDto> respDtoList = null;
        respDtoList = reservations.stream().map(reservation -> {
            int userId = reservation.getUserId();
            Account userAccount = authMapper.findAccountByUserId(userId);
            return MyTodayScheduleRespDto.builder()
                    .reservationId(reservation.getReservationId())
                    .trainerId(reservation.getTrainerId())
                    .timeId(reservation.getTimeId())
                    .timeDuration(reservation.getTime().getTimeDuration())
                    .phone(userAccount.getPhone())
                    .name(userAccount.getName())
                    .build();
        }).collect(Collectors.toList());
        return respDtoList;
    }

    public int getTrainerId(int accountId) {
        int trainerId = authMapper.findTrainerIdByAccountId(accountId);

        return trainerId;
    }

    public List<SearchReservationUserRespDto> searchReservationsUser (SearchReservationUserReqDto reqDto) {
        String startDate = CommonService.trimDateString(reqDto.getStartDate());
        String endDate = CommonService.trimDateString(reqDto.getEndDate());
        List<Reservation> reservations = reservationMapper.findReservationByAccountIdAndPeriod(reqDto.getAccountId(), startDate, endDate);
        List<SearchReservationUserRespDto> respDtos = reservations.stream().map(reservation -> {
                Account userAccount = authMapper.findAccountByUserId(reservation.getUserId());
                return SearchReservationUserRespDto.builder()
                        .UserId(reservation.getUserId())
                        .name(userAccount.getName())
                        .reservationDate(reservation.getReservationDate())
                        .timeDuration(reservation.getTime().getTimeDuration())
                        .build();
                }
        ).collect(Collectors.toList());
        return respDtos;
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
