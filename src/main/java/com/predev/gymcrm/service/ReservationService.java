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

    public void insertReservation(UserAddReservationReqDto reqDto) {
        int userId = authMapper.findUserIdByAccountId(reqDto.getAccountId());
        String date = reqDto.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        reservationMapper.saveReservation(reqDto.toReservationEntity(date, userId));
        int trainerAccountId = authMapper.findAccountByTrainerId(reqDto.getTrainerId()).getAccountId();
        String userName = authMapper.findAccountByAccountId(reqDto.getAccountId()).getName();
        String title = "예약알림";
        String message = userName+"님이 "+ date + " " + (reqDto.getTimeId() + 9) + ":00~" + (reqDto.getTimeId() + 10) + ":00 타임 예약 하였습니다.";
        fcmPushNotificationService.sendFCMOneToOne(trainerAccountId, title, message);
    }

    public List<AdminSearchReservationRespDto> searchReservationsByUserId(int accountId) {
        int userId = authMapper.findUserIdByAccountId(accountId);
        List<Reservation> reservations = reservationMapper.findReservationsByUserId(userId);
        return reservations.stream().map(Reservation::toSearchReservationRespDto).collect(Collectors.toList());
    }

    public List<Time> searchDayReservation(SearchDayReservationReqDto reqDto) {
        int userId = 0;
        try{
            userId = authMapper.findUserIdByAccountId(reqDto.getAccountId());
        } catch (Exception e) {
            e.getMessage();
        }
        List<Reservation> reservations = reservationMapper.findReservationByDate(userId, reqDto.getTrainerId(), TimeService.trimDateString(reqDto.getDate()));
        return reservations.stream().map(Reservation::getTime).collect(Collectors.toList());
    }



    public List<TrainerSearchScheduleRespDto> searchTodayReservation(TrainerSearchTodayScheduleReqDto reqDto) {
        String today = TimeService.trimDateString(reqDto.getToday());
        List<Reservation> reservations = reservationMapper.findTodayReservation(reqDto.getTrainerId(), today);
        return reservations.stream().map(Reservation::toMyTodayScheduleRespDto).collect(Collectors.toList());

    }

    public List<AdminSearchReservationUserRespDto> searchReservationsUser (TrainerSearchReservationUserReqDto reqDto) {
        String startDate = TimeService.trimDateString(reqDto.getStartDate());
        String endDate = TimeService.trimDateString(reqDto.getEndDate());
        List<Reservation> reservations = reservationMapper.findReservationByAccountIdAndPeriod(reqDto.getAccountId(), startDate, endDate);
        return reservations.stream().map(Reservation::toSearchReservationUserRespDto).collect(Collectors.toList());
    }

    public int deleteReservationByReservationId(int reservationId) {
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

    public void editReservation(UserEditReservationReqDto reqDto) {
        String date = trimDateString(reqDto.getDate());
        int userId = authMapper.findUserIdByAccountId(reqDto.getAccountId());
        reservationMapper.updateReservationByReservationId(reqDto.getPrevReservationId(), reqDto.toReservationEntity(date,userId));
    }

    public List<TrainerSearchMyMembersInformationRespDto> searchMymembersInformation(TrainerSearchMembersInformationReqDto reqDto) {
        List<Reservation> reservations = reservationMapper.findMyMembersInformationByAccountIdByUserId(reqDto.getAccountId(), reqDto.getUserId());
        return reservations.stream().map(Reservation::toSelectMyMembersInformationRespDto).collect(Collectors.toList());
    }

    public List<AdminSearchReservationRespDto> searchReservations(AdminSearchReservationReqDto reqDto) {
        String startDate = TimeService.trimDateString(reqDto.getStartDate());
        String endDate = TimeService.trimDateString(reqDto.getEndDate());
        List<Reservation> reservations = reservationMapper.findReservationByNameAndPeriod(reqDto.getSearchType(), reqDto.getName(), startDate, endDate);
        return reservations.stream().map(Reservation::toSearchReservationRespDto).collect(Collectors.toList());
    }

    public List<TrainerSearchMyMembersRespDto> searchMyMembers(int trainerAccountId) {
        List<Reservation> reservations = reservationMapper.findMyMembersByTrainerAccountId(trainerAccountId);
        return reservations.stream().map(Reservation::toSearchMyMembersRespDto).collect(Collectors.toList());
    }

}
