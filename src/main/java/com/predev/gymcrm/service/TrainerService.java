package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.AdminSearchUnreservedTrainerReqDto;
import com.predev.gymcrm.dto.req.UpdateTrainerProfileImgReqDto;
import com.predev.gymcrm.dto.resp.*;
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
public class TrainerService {

    @Autowired
    private TrainerMapper trainerMapper;

    public TrainerSearchInfoRespDto searchAllTrainerInfo(int accountId) {
        TrainerAccountView trainer = trainerMapper.findAllTrainerInfo(accountId);
        if(trainer != null) {
            return trainer.toTrainerInfoRespDto();
        }
        return null;
    }

    public List<TrainerSearchForReservationRespDto> searchTrainersForReservation() {
        List<TrainerAccountView> trainers = trainerMapper.findTrainers();
        return trainers.stream().map(TrainerAccountView::trainerForReservationRespDto).collect(Collectors.toList());
    }
    public void editTrainerProfileImg(UpdateTrainerProfileImgReqDto reqDto) {
        trainerMapper.updateTrainerProfileImgUrl(reqDto.toEntity());
    }
    public List<UserSearchUnreservedTrainerRespDto> searchUnreservedTrainers(AdminSearchUnreservedTrainerReqDto reqDto) {
        List<TrainerAccountView> trainers = trainerMapper.findAvailableTrainerAtDayAndTime(TimeService.trimDateString(reqDto.getDate()), reqDto.getTimeId());
        List<UserSearchUnreservedTrainerRespDto> respDtos = trainers.stream().map(TrainerAccountView::toSearchUnreservedTrainerRespDto).collect(Collectors.toList());
        return respDtos;
    }
    public List<AdminSearchTrainerRespDto> searchAdminTrainers() {
        List<AdminSearchTrainer> adminSearchTrainers = trainerMapper.findAdminSearchTrainers();
        return adminSearchTrainers.stream().map(AdminSearchTrainer::toAdminSearchTrainerRespDto).collect(Collectors.toList());
    }
    public List<AdminSearchWeeklyTrainerReservationCountsRespDto> searchWeeklyTrainerReservationCounts() {
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
