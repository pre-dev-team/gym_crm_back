package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Trainer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TrainerMapper {
    public int saveTrainer(Trainer trainer);
    public Trainer findTrainerByTrainerUsername(String TrainerUsername);

    public List<Trainer> findAllTrainers();
}
