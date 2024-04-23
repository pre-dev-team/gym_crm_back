package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Holiday;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HolidayMapper {

    int saveHoliday(
            @Param("timeIds") List<Integer> timeIds,
            @Param("holiday") Holiday holiday
    );

    int deleteHoliday(Holiday holiday);
    int determineHolidayConfirm(
            @Param("trainerId") int trainerId,
            @Param("holidayDate") String holidayDate,
            @Param("status") boolean status
    );
    List<Holiday> getAllHolidays();
    List<Holiday> selectHolidayByAccountId(int accountId);



}
