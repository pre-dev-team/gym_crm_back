package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.TrainerHolidayReqDto;
import com.predev.gymcrm.entity.Holiday;
import com.predev.gymcrm.repository.HolidayMapper;
import com.predev.gymcrm.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private HolidayMapper holidayMapper;


    @PostMapping("/insert")
    public ResponseEntity<?> saveTrainerHoliday(@RequestBody TrainerHolidayReqDto trainerHolidayReqDto) {
        holidayService.insertTrainerHoliday(trainerHolidayReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHoliday(@RequestBody Holiday holiday) {
        holidayMapper.deleteHoliday(holiday);
        return ResponseEntity.ok(null);
    }
}
