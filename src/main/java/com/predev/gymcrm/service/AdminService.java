package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.AdminSearchReservationReqDto;
import com.predev.gymcrm.dto.resp.AdminSearchTrainerRespDto;
import com.predev.gymcrm.dto.resp.AdminSearchUserRespDto;
import com.predev.gymcrm.dto.resp.SearchReservationRespDto;
import com.predev.gymcrm.entity.Reservation;
import com.predev.gymcrm.entity.Trainer;
import com.predev.gymcrm.entity.User;
import com.predev.gymcrm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private TrainerMapper trainerMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private AdminMapper adminMapper;

    public List<AdminSearchUserRespDto> SearchUsersByName(String name) {
        List<User> users = authMapper.findUsersByName(name);
        if (users.size() == 0) {
            return null;
        }
        return users.stream().map(user ->
                AdminSearchUserRespDto.builder()
                        .userId(user.getUserId())
                        .name(user.getAccount().getName())
                        .ReservationCount(
                                reservationMapper.findReservationCountByUserId(user.getUserId())
                        )
                        .build()
        ).collect(Collectors.toList());
    }

    public List<AdminSearchTrainerRespDto> SearchTrainers() {
        List<Trainer> trainers = trainerMapper.getTrainers();
        return trainers.stream()
                .map(trainer -> AdminSearchTrainerRespDto.builder()
                        .trainerId(trainer.getTrainerId())
                        .name(trainer.getAccount().getName())
                        .memberCount(
                                reservationMapper.findMemberCountOfTrainerByTrainerId(
                                        trainer.getTrainerId()
                                )
                        )
                        .avgScore(
                                reviewMapper.findAvgReviewScoreByTrainerId(
                                        trainer.getTrainerId()
                                )
                        )
                        .build()
                ).collect(Collectors.toList());
    }

    public List<SearchReservationRespDto> SearchReservations(AdminSearchReservationReqDto reqDto) {
        String startDate = CommonService.trimDateString(reqDto.getStartDate());
        String endDate = CommonService.trimDateString(reqDto.getEndDate());
        List<Reservation> reservations = reservationMapper.findReservationByNameAndPeriod(
                reqDto.getSearchType(),
                reqDto.getName(),
                startDate,
                endDate
        );

        if (reqDto.getSearchType() == 2) {
            return reservations.stream().map(reservation -> SearchReservationRespDto.builder()
                    .reservationId(reservation.getReservationId())
                    .reservationDate(reservation.getReservationDate())
                    .timeDuration(reservation.getTime().getTimeDuration())
                    .name(
                            authMapper.findAccountByUserId(reservation.getUserId())
                                    .getName()
                    )
                    .trainerName(reservation.getTrainer().getAccount().getName())
                    .build()
            ).collect(Collectors.toList());
        }

        return reservations.stream().map(reservation -> SearchReservationRespDto.builder()
                .reservationId(reservation.getReservationId())
                .reservationDate(reservation.getReservationDate())
                .timeDuration(reservation.getTime().getTimeDuration())
                .name(reservation.getUser().getAccount().getName())
                .trainerName(
                        authMapper.findAccountByTrainerId(reservation.getTrainerId())
                                .getName()
                )
                .build()
        ).collect(Collectors.toList());
    }
}
