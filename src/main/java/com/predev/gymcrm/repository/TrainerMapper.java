package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.Holiday;
import com.predev.gymcrm.entity.Reservation;
import com.predev.gymcrm.entity.Trainer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrainerMapper {

    List<Trainer> getTrainers();
    List<Integer> findReservedUserIdsByTrainerAccountId(int trainerAccountId);
    Trainer getAllTrainerInfo(int accountId);
    int updateTrainerProfileImgUrl(Trainer trainer);

}
