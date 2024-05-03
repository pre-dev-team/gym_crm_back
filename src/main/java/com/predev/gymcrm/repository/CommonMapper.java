package com.predev.gymcrm.repository;
import com.predev.gymcrm.entity.Time;
import com.predev.gymcrm.entity.Trainer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CommonMapper {
    List<Time> getTimes();
}
