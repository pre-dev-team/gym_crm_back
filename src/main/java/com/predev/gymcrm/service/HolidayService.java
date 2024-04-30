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
        return holidays.stream().map(holiday -> holiday.toSelectHolidayRespDto(account.getName())).collect(Collectors.toList());
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

    public List<Integer> getHolidaytimeIdsByTrainerIdAndHolidayDate(int trainerId, String holidayDate) {
        String trimedHolidayDate = CommonService.trimDateString(holidayDate);
        List<Holiday> holidays = holidayMapper.findHolidayByTrainerIdAndDate(trainerId,trimedHolidayDate);
        List<Integer> timeIds = new ArrayList<>();
        if(!holidays.isEmpty()) {
            timeIds = holidays.stream().map(Holiday::getTimeId).collect(Collectors.toList());
        }
        return timeIds;
    }


}
