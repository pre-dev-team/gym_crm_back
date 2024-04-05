package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.resp.TimeRespDto;
import com.predev.gymcrm.entity.Time;
import com.predev.gymcrm.repository.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonService {

    @Autowired
    private CommonMapper commonMapper;

    public List<TimeRespDto> getTimes() {
        List<Time> times = commonMapper.getTimes();
        return times.stream()
                .map(Time::toTimeRespDto)
                .collect(Collectors.toList());
    }

}