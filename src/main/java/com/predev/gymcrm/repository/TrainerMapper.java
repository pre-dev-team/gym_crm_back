package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrainerMapper {
    public List<Integer> findReservedUserIdsByTrainerAccountId(int trainerAccountId);
}
