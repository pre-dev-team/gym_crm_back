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
    private final CommonMapper commonMapper;

    @Autowired
    public CommonService(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }


    // 시간대 정보를 가져와서 TimeRespDto 형식으로 변환하여 반환
    public List<TimeRespDto> getTimes() {
        // CommonMapper를 통해 시간대 정보를 가져옴
        List<Time> times = commonMapper.getTimes();
        // 시간대 정보를 TimeRespDto 형식으로 변환하여 반환
        return times.stream()
                .map(this::convertToRespDto)
                .collect(Collectors.toList());
    }

    // Time 객체를 TimeRespDto로 변환하는 메서드
    private TimeRespDto convertToRespDto(Time time) {
        return TimeRespDto.builder()
                .timeId(time.getTimeId())
                .timePeriod(time.getTimePeriod())
                .build();
    }

}
