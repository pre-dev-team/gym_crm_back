package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.RoutineMakeReqDto;
import com.predev.gymcrm.dto.req.TrainerHolidayReqDto;
import com.predev.gymcrm.dto.req.UpdateTrainerProfileImgReqDto;
import com.predev.gymcrm.dto.resp.RoutineMakeRespDto;
import com.predev.gymcrm.dto.resp.SearchMyMembersRespDto;
import com.predev.gymcrm.dto.resp.TrainerForReservationRespDto;
import com.predev.gymcrm.dto.resp.TrainerInfoRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.Reservation;
import com.predev.gymcrm.entity.Trainer;
import com.predev.gymcrm.entity.WorkoutRoutine;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.CommonMapper;
import com.predev.gymcrm.repository.ReservationMapper;
import com.predev.gymcrm.repository.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    @Autowired
    private TrainerMapper trainerMapper;

    @Autowired
    private AuthMapper authMapper;



    public List<SearchMyMembersRespDto> selectMyMembers(int trainerAccountId) {
        List<Reservation> reservations = trainerMapper.findMyMembersByTrainerAccountId(trainerAccountId);
        return reservations.stream().map(Reservation::toSearchMyMembersRespDto).collect(Collectors.toList());
    }

    public TrainerInfoRespDto getAllTrainerInfo(int accountId) {
        Trainer trainer = trainerMapper.getAllTrainerInfo(accountId);
        if(trainer != null) {
            return TrainerInfoRespDto.builder()
                    .trainerId(trainer.getTrainerId())
                    .name(trainer.getAccount().getName())
                    .username(trainer.getAccount().getUsername())
                    .phone(trainer.getAccount().getPhone())
                    .email(trainer.getAccount().getEmail())
                    .trainerProfileImgUrl(trainer.getTrainerProfileImgUrl())
                    .build();
        }

        return null;
    }

    public List<TrainerForReservationRespDto> getTrainersForReservation() {
        List<Trainer> trainers = trainerMapper.getTrainers();
        return trainers.stream().map(trainer -> TrainerForReservationRespDto.builder()
                .trainerId(trainer.getTrainerId())
                .trainerProfileImgUrl(trainer.getTrainerProfileImgUrl())
                .name(trainer.getAccount().getName())
                .build()).collect(Collectors.toList());
    }

    public void updateTrainerProfileImg(UpdateTrainerProfileImgReqDto reqDto) {
        trainerMapper.updateTrainerProfileImgUrl(reqDto.toEntity());

    }
      
    public void makeRoutine(List<RoutineMakeReqDto> routineMakeReqDtos) {
        List<WorkoutRoutine> workoutRoutines = routineMakeReqDtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
        int successCount = trainerMapper.saveRoutines(workoutRoutines);
        System.out.println(successCount);

    }



}
