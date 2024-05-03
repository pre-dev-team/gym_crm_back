package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.AdminSearchReservationReqDto;
import com.predev.gymcrm.dto.resp.AdminSearchTrainerRespDto;
import com.predev.gymcrm.dto.resp.AdminSearchUserRespDto;
import com.predev.gymcrm.dto.resp.AdminSearchWeeklyTrainerReservationCountsRespDto;
import com.predev.gymcrm.dto.resp.SearchReservationRespDto;
import com.predev.gymcrm.entity.*;
import com.predev.gymcrm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

//    public List<AdminSearchTrainerRespDto> SearchTrainers() {
//        List<TrainerAccountView> trainers = trainerMapper.findTrainers();
//        return trainers.stream()
//                .map(trainer -> AdminSearchTrainerRespDto.builder()
//                        .trainerId(trainer.getTrainerId())
//                        .name(trainer.getName())
//                        .memberCount(
//                                reservationMapper.findMemberCountOfTrainerByTrainerId(
//                                        trainer.getTrainerId()
//                                )
//                        )
//                        .avgScore(
//                                reviewMapper.findAvgReviewScoreByTrainerId(
//                                        trainer.getTrainerId()
//                                )
//                        )
//                        .build()
//                ).collect(Collectors.toList());
//    }

    public List<SearchReservationRespDto> SearchReservations(AdminSearchReservationReqDto reqDto) {
        String startDate = TimeService.trimDateString(reqDto.getStartDate());
        String endDate = TimeService.trimDateString(reqDto.getEndDate());
        List<Reservation> reservations = reservationMapper.findReservationByNameAndPeriod(
                reqDto.getSearchType(),
                reqDto.getName(),
                startDate,
                endDate
        );
        return null;
    }

    public List<AdminSearchWeeklyTrainerReservationCountsRespDto> getWeeklyTrainerReservationCounts() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate firstDayOfFirstWeek = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        List<Map<String, String>> weekData = new ArrayList<>();
        for(int i=0; i<4; i++) {
            weekData.add(
                    Map.of(
                    "startDate",firstDayOfFirstWeek.plusDays(i*(7)).toString(),
                    "endDate",firstDayOfFirstWeek.plusDays(i*(7)+6).toString()
                    )
            );
        }
        return trainerMapper.findWeeklyTrainerReservationCounts(weekData).stream()
                .map(WeeklyTrainerReservationCounts::toRespDto).collect(Collectors.toList());
    }

}
