package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.resp.TimeRespDto;
import com.predev.gymcrm.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/duration")
    public ResponseEntity<?> getDuration() {
        List<TimeRespDto> timePeriods = timeService.searchTimes();
        return ResponseEntity.ok(timePeriods);
    }



}