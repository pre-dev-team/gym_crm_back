package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrainerMapper {

    List<TrainerAccountView> findTrainers();
    TrainerAccountView findAllTrainerInfo(int accountId);
    int updateTrainerProfileImgUrl(Trainer trainer);
    List<WeeklyTrainerReservationCounts> findWeeklyTrainerReservationCounts(List<Map<String, String>> weekData);
    List<TrainerAccountView> findAvailableTrainerAtDayAndTime(
            @Param("reservationDate") String reservationDate,
            @Param("timeId") int timeId
    );
    String findTrainerProfileImgUrl(int trainerId);
}
