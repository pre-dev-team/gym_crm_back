package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Trainer;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TrainerMapper {
    public int saveTrainer(Trainer trainer);

}
