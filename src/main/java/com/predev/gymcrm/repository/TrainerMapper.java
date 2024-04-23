package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrainerMapper {

    List<Trainer> getTrainers();
    List<Integer> findReservedUserIdsByTrainerAccountId(int trainerAccountId);
    Trainer getAllTrainerInfo(int accountId);
    int updateTrainerProfileImgUrl(Trainer trainer);
    int saveRoutines(List<WorkoutRoutine> workoutRoutines);

}
