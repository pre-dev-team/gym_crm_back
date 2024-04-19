package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.CancelHolidayReqDto;
import com.predev.gymcrm.dto.req.TrainerHolidayReqDto;
import com.predev.gymcrm.dto.resp.SelectHolidayRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.Holiday;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.HolidayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayService {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private HolidayMapper holidayMapper;

    public void insertTrainerHoliday(TrainerHolidayReqDto reqDto) {
        int trainerId = authMapper.findTrainerIdByAccountId(reqDto.getAccountId());
        String date = CommonService.trimDateString(reqDto.getHolidayDate());
        List<Integer> timeIds = new ArrayList<>();
        for(int i = reqDto.getStartTimeId(); i < reqDto.getEndTimeId() + 1; i++) {
            timeIds.add(i);
        }
        holidayMapper.saveHoliday(timeIds, reqDto.toTrainerHolidayEntity(date, trainerId));
    }

    public void deleteHoliday(CancelHolidayReqDto reqDto) {
        int trainerId = authMapper.findTrainerIdByAccountId(reqDto.getAccountId());
        String date = CommonService.trimDateString(reqDto.getHolidayDate());
        holidayMapper.deleteHoliday(reqDto.toDeleteHolidayEntity(date, trainerId));
    }

    public List<SelectHolidayRespDto> selectHoliday(int accountId) {
        List<Holiday> holidays = holidayMapper.selectHolidayByAccountId(accountId);
        Account account = authMapper.findAccountByAccountId(accountId);
        List<SelectHolidayRespDto> respDtos = holidays.stream().map(holiday ->
                SelectHolidayRespDto.builder()
                        .holidayId(holiday.getHolidayId())
                        .holidayDate(holiday.getHolidayDate())
                        .TimeId(holiday.getTimeId())
                        .name(account.getName())
                        .build()
        ).collect(Collectors.toList());
        return respDtos;
    }
}
