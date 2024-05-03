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

    @Autowired
    FCMPushNotificationService fcmPushNotificationService;

    public String trimDateString(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public List<SearchReservationRespDto> findAll() {
        return reservationMapper.findAllReservation().stream()
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

        int trainerAccountId = authMapper.findAccountByTrainerId(reqDto.getTrainerId()).getAccountId();
        System.out.println(trainerAccountId);
        String userName = authMapper.findAccountByAccountId(reqDto.getAccountId()).getName();
        String title = "예약알림";
        String message = userName+"님이 "+ date + " " + (reqDto.getTimeId() + 9) + ":00~" + (reqDto.getTimeId() + 10) + ":00 타임 예약 하였습니다.";
        fcmPushNotificationService.sendFCMOneToOne(trainerAccountId, title, message);
    }

    public List<SearchReservationRespDto> searchReservationsByUserId(int accountId) {
        int userId = authMapper.findUserIdByAccountId(accountId);
        List<Reservation> reservations =reservationMapper.findReservationsByUserId(userId);

        return reservations.stream().map(reservation -> {
            return SearchReservationRespDto.builder()
                    .reservationId(reservation.getReservationId())
                    .reservationDate(reservation.getReservationDate())
                    .trainerId(reservation.getTrainerId())
                    .name(reservation.getTrainer().getAccount().getName())
                    .timeId(reservation.getTimeId())
                    .timeDuration(reservation.getTime().getTimeDuration())
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
        List<Reservation> reservations = reservationMapper.findReservationByDate(userId, reqDto.getTrainerId(), TimeService.trimDateString(reqDto.getDate()));
        return reservations.stream().map(Reservation::getTime).collect(Collectors.toList());
    }



    public List<MyTodayScheduleRespDto> getTodayReservation(MyTodayScheduleReqDto reqDto) {
        String today = TimeService.trimDateString(reqDto.getToday());
        // 예약 정보를 가져오는 비지니스 로직 작성
        // 예시로 비지니스 로직을 호출하여 예약 정보를 가져오는 코드 작성
        List<Reservation> reservations = reservationMapper.findTodayReservation(reqDto.getTrainerId(), today);
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
        String startDate = TimeService.trimDateString(reqDto.getStartDate());
        String endDate = TimeService.trimDateString(reqDto.getEndDate());
        List<Reservation> reservations = reservationMapper.findReservationByAccountIdAndPeriod(reqDto.getAccountId(), startDate, endDate);
        return reservations.stream().map(Reservation::toSearchReservationUserRespDto).collect(Collectors.toList());
    }

    public int cancelReservationByReservationId(int reservationId) {
        Reservation reservation = reservationMapper.findReservationByReservationId(reservationId);
        String userName = reservation.getUserAccountView().getName();
        int trainerAccountId = reservation.getTrainerAccountView().getAccountId();
        int timeId = reservation.getTimeId();
        String date = reservation.getReservationDate();
        String title = "예약 취소알림";
        String message = userName+"님이 "+ date + " " + (timeId + 9) + ":00~" + (timeId + 10) + ":00 타임 예약을 취소 하였습니다.";
        fcmPushNotificationService.sendFCMOneToOne(trainerAccountId, title, message);
        return reservationMapper.deleteReservationByReservationId(reservationId);
    }

    public void updateReservation(EditReservationReqDto reqDto) {
        String date = trimDateString(reqDto.getDate());
        int userId = authMapper.findUserIdByAccountId(reqDto.getAccountId());
        reservationMapper.updateReservationByReservationId(reqDto.getPrevReservationId(), reqDto.toReservationEntity(date,userId));
    }

    public List<SelectMyMembersInformationRespDto> selectMymembersInformation(SearchMymembersInformationReqDto reqDto) {
        List<Reservation> reservations = reservationMapper.findMyMembersInformationByAccountIdByUserId(reqDto.getAccountId(), reqDto.getUserId());
        return reservations.stream().map(Reservation::toSelectMyMembersInformationRespDto).collect(Collectors.toList());
    }

    public List<SearchReservationRespDto> SearchReservations(AdminSearchReservationReqDto reqDto) {
        String startDate = TimeService.trimDateString(reqDto.getStartDate());
        String endDate = TimeService.trimDateString(reqDto.getEndDate());

        return null;
    }
}
