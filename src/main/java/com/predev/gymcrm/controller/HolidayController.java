package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.CancelHolidayReqDto;
import com.predev.gymcrm.dto.req.TrainerHolidayReqDto;
import com.predev.gymcrm.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;


    @PostMapping("/insert")
    public ResponseEntity<?> saveTrainerHoliday(@RequestBody TrainerHolidayReqDto trainerHolidayReqDto) {
        holidayService.insertTrainerHoliday(trainerHolidayReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHoliday(@RequestBody CancelHolidayReqDto cancelHolidayReqDto) {
        System.out.println(cancelHolidayReqDto);
        holidayService.deleteHoliday(cancelHolidayReqDto);
        return ResponseEntity.ok(cancelHolidayReqDto);
    }

    @GetMapping("/select")
    public ResponseEntity<?> selectHolidays(@RequestParam(value = "accountId") int accountId) {
        return ResponseEntity.ok(holidayService.selectHoliday(accountId));
    }
}
