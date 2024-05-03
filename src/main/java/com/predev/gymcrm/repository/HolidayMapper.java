package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.AdminSearchHoliday;
import com.predev.gymcrm.entity.Holiday;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface HolidayMapper {

    int saveHoliday(
            @Param("timeIds") List<Integer> timeIds,
            @Param("holiday") Holiday holiday
    );

    int deleteHoliday(Holiday holiday);
    int updateHolidayConfirm(
            @Param("trainerId") int trainerId,
            @Param("holidayDate") String holidayDate,
            @Param("status") boolean status
    );
    List<Holiday> findHolidayByTrainerIdAndDate(
            @Param("trainerId") int trainerId,
            @Param("holidayDate") String date
    );
    List<AdminSearchHoliday> findAllAdminSearchHolidyByTrainerId(
            @Param("trainerId") int trainerId,
            @Param("status") int status
    );
    List<Holiday> findHolidayByAccountId(int accountId);
    List<Holiday> findHolidayByTrainerIdByHolidayDateByTimeId(
            @Param("trainerId") int trainerId,
            @Param("holidayDate") String holidayDate,
            @Param("startTimeId") int startTimeId,
            @Param("endTimeId") int endTimeId
    );

}
