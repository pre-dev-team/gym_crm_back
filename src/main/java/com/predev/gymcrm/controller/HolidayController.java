package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.TrainerHolidayReqDto;
import com.predev.gymcrm.repository.HolidayMapper;
import com.predev.gymcrm.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;


    @PostMapping("/insert")
    public ResponseEntity<?> saveTrainerHoliday(@RequestBody TrainerHolidayReqDto trainerHolidayReqDto) {
        System.out.println(trainerHolidayReqDto);
        holidayService.insertTrainerHoliday(trainerHolidayReqDto);
        return ResponseEntity.created(null).body(true);
    }
}
