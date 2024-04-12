package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.resp.SearchMyMembersRespDto;
import com.predev.gymcrm.dto.resp.TrainerForReservationRespDto;
import com.predev.gymcrm.dto.resp.TrainerInfoRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.Trainer;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.ReservationMapper;
import com.predev.gymcrm.repository.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    @Autowired
    private TrainerMapper trainerMapper;

    @Autowired
    private AuthMapper authMapper;

    public List<SearchMyMembersRespDto> selectMyMembers(int trainerAccountId) {
        List<Integer> userIds = trainerMapper.findReservedUserIdsByTrainerAccountId(trainerAccountId);
        List<Account> myMembers = userIds.stream().map(id -> authMapper.findAccountByUserId(id)).collect(Collectors.toList());
        return myMembers.stream().map(account -> SearchMyMembersRespDto.builder()
                .accountId(account.getAccountId())
                .name(account.getName())
                .phone(account.getPhone())
                .email(account.getEmail())
                .build()
        ).collect(Collectors.toList());
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
        System.out.println(trainers);
        return trainers.stream().map(trainer -> TrainerForReservationRespDto.builder()
                .trainerId(trainer.getTrainerId())
                .trainerProfileImgUrl(trainer.getTrainerProfileImgUrl())
                .name(trainer.getAccount().getName())
                .build()).collect(Collectors.toList());
    }

}
