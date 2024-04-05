package com.predev.gymcrm.repository;
import com.predev.gymcrm.entity.Time;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CommonMapper {
    // 시간대 정보를 가져오는 쿼리
    List<Time> getTimes();
}
