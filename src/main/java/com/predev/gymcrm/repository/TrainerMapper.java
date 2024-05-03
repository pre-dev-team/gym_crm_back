package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrainerMapper {

    List<Trainer> getTrainers();
    List<Reservation> findMyMembersByTrainerAccountId(int trainerAccountId);
    Trainer getAllTrainerInfo(int accountId);
    int updateTrainerProfileImgUrl(Trainer trainer);
    List<WeeklyTrainerReservationCounts> findWeeklyTrainerReservationCounts(List<Map<String, String>> weekData);
    String getTrainerProfileImgUrl(int trainerId);
}
