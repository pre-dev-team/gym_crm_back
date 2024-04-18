package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.resp.TimeRespDto;
import com.predev.gymcrm.dto.resp.TrainerForReservationRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.Time;
import com.predev.gymcrm.entity.Trainer;
import com.predev.gymcrm.repository.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonService {

    @Autowired
    private CommonMapper commonMapper;

    public static String trimDateString(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    public List<TimeRespDto> getTimes() {
        List<Time> times = commonMapper.getTimes();
        return times.stream()
                .map(Time::toTimeRespDto)
                .collect(Collectors.toList());
    }



}