package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.Trainer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrainerMapper {
    public List<Integer> findReservedUserIdsByTrainerAccountId(int trainerAccountId);
    public Trainer getAllTrainerInfo(int trainerId);
}
