package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.resp.TimeRespDto;
import com.predev.gymcrm.entity.Time;
import com.predev.gymcrm.repository.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    private TimeMapper timeMapper;

    public static String trimDateString(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public List<TimeRespDto> searchTimes() {
        List<Time> times = timeMapper.findTimes();
        return times.stream()
                .map(Time::toTimeRespDto)
                .collect(Collectors.toList());
    }

}