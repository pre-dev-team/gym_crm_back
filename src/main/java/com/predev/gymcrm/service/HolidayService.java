package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.AdminDecideHolidayAppliesReqDto;
import com.predev.gymcrm.dto.req.CancelHolidayReqDto;
import com.predev.gymcrm.dto.req.TrainerHolidayReqDto;
import com.predev.gymcrm.dto.resp.AdminSearchHolidayRespDto;
import com.predev.gymcrm.dto.resp.SelectHolidayRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.AdminSearchHoliday;
import com.predev.gymcrm.entity.Holiday;
import com.predev.gymcrm.exception.HolidayException;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.HolidayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

        List<Holiday> isAlreadyHoliday = holidayMapper.searchHolidayByTrainerIdByHolidayDateByTimeId(
                trainerId, date, reqDto.getStartTimeId(), reqDto.getEndTimeId()
        );

        if (!isAlreadyHoliday.isEmpty()) {
            throw new HolidayException("이미 신청된 연차입니다.");
        }

        holidayMapper.saveHoliday(timeIds, reqDto.toTrainerHolidayEntity(date, trainerId));
    }

    public void deleteHoliday(CancelHolidayReqDto reqDto) {
        int trainerId = authMapper.findTrainerIdByAccountId(reqDto.getAccountId());

        holidayMapper.deleteHoliday(reqDto.toDeleteHolidayEntity(trainerId));
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
                        .confirm(holiday.getConfirm())
                        .build()
        ).collect(Collectors.toList());
        return respDtos;
    }
    public Map<String, List<Holiday>> sortHolidayByConfirm(List<Holiday> holidays) {
        List<Holiday> confirmedHolidays =
                holidays.stream().filter(holiday -> holiday.getConfirm() != 0).collect(Collectors.toList());
        List<Holiday> unconfirmedHolidays =
                holidays.stream().filter(holiday -> holiday.getConfirm() == 0).collect(Collectors.toList());
        return Map.of("confirmed", confirmedHolidays, "unconfirmed", unconfirmedHolidays);
    }

    public Map<String, List<Holiday>> sortHolidayByHolidayDate(List<Holiday> holidays) {
        Map<String, List<Holiday>> result = new HashMap<>();
        Map<String, List<Holiday>> sortedResult = holidays.stream().collect(Collectors.groupingBy(Holiday::getHolidayDate));
        Set<String> strings = sortedResult.keySet();
        System.out.println(strings);
        for(String string : strings) {
            int min = sortedResult.get(string).stream().map(Holiday::getTimeId).min(Integer::compareTo).orElseGet(() -> 0);
            int max = sortedResult.get(string).stream().map(Holiday::getTimeId).max(Integer::compareTo).orElseGet(() -> 0);
            List<Holiday> minAndMaxHolidays = new ArrayList<>();
            Holiday tempHoliday1 = sortedResult.get(string).get(0);
            tempHoliday1.setTimeId(min);
            Holiday tempHoliday2 = sortedResult.get(string).get(1);
            tempHoliday2.setTimeId(max);
            minAndMaxHolidays.add(tempHoliday1);
            minAndMaxHolidays.add(tempHoliday2);
            result.put(string, minAndMaxHolidays);
        }
        return result;
    }

    public List<AdminSearchHolidayRespDto> getUnconfirmedHolidays(int trainerId) {
        List<AdminSearchHoliday> holidays = holidayMapper.getAllAdminSearchHolidyByTrainerId(trainerId, 1);
        return holidays.stream().map(AdminSearchHoliday::toAdminSearchHolidayRespDto).collect(Collectors.toList());
    }
    public List<AdminSearchHolidayRespDto> getConfirmedHolidays(int trainerId) {
        List<AdminSearchHoliday> holidays = holidayMapper.getAllAdminSearchHolidyByTrainerId(trainerId, 2);
        return holidays.stream().map(AdminSearchHoliday::toAdminSearchHolidayRespDto).collect(Collectors.toList());
    }

    public int decideHolidayApplies(AdminDecideHolidayAppliesReqDto reqDto) {
        return holidayMapper.determineHolidayConfirm(
                reqDto.getTrainerId(),
                reqDto.getHolidayDate(),
                reqDto.isStatus()
        );
    }
}
