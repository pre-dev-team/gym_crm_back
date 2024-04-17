package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.TrainerHolidayReqDto;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.HolidayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private HolidayMapper holidayMapper;

    public void insertTrainerHoliday(TrainerHolidayReqDto reqDto) {
        int trainerId = authMapper.findTrainerIdByAccountId(reqDto.getAccountId());
        String date = CommonService.trimDateString(reqDto.getHolidayDate());
        List<Integer> timeIds = null;
        for(int i = reqDto.getStartTimeId(); i < reqDto.getEndTimeId() + 1; i++) {
            timeIds.add(i);
        }

//        holidayMapper.saveHoliday(reqDto.toTrainerHolidayEntity(date, trainerId));
    }
}
