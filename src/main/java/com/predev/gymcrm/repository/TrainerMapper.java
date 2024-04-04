package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Trainer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;


@Mapper
public interface TrainerMapper {
    public int saveTrainer(Trainer trainer);
    Trainer findTrainerByTrainerUsername(String trainerUsername);

    public List<Trainer> findTrainers(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("trainerId") int trainerId
    );
}
