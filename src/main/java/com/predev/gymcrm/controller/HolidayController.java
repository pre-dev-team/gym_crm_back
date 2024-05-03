package com.predev.gymcrm.controller;

import com.predev.gymcrm.aop.annotation.ValidAspect;
import com.predev.gymcrm.dto.req.AdminDecideHolidayAppliesReqDto;
import com.predev.gymcrm.dto.req.CancelHolidayReqDto;
import com.predev.gymcrm.dto.req.TrainerHolidayReqDto;
import com.predev.gymcrm.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @PostMapping("/insert")
    public ResponseEntity<?> addTrainerHoliday(@RequestBody TrainerHolidayReqDto trainerHolidayReqDto) {
        holidayService.insertTrainerHoliday(trainerHolidayReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHoliday(@RequestBody CancelHolidayReqDto cancelHolidayReqDto) {
        holidayService.deleteHoliday(cancelHolidayReqDto);
        return ResponseEntity.ok(cancelHolidayReqDto);
    }

    @GetMapping("/select")
    public ResponseEntity<?> getHolidays(@RequestParam(value = "accountId") int accountId) {
        return ResponseEntity.ok(holidayService.selectHoliday(accountId));
    }

    @GetMapping("/find")
    public ResponseEntity<?> getHolidayTimeIds(@RequestParam int trainerId, @RequestParam String holidayDate) {
        return ResponseEntity.ok(holidayService.selectHolidaytimeIdsByTrainerIdAndHolidayDate(trainerId,holidayDate));
    }

    @GetMapping("/admin/unconfirmed")
    public ResponseEntity<?> getUnconfirmedHolidayApplies(@RequestParam(value = "trainerId") int trainerId) {
        return ResponseEntity.ok(holidayService.getUnconfirmedHolidays(trainerId));
    }

    @GetMapping("/admin/confirmed")
    public ResponseEntity<?> getConfirmedHolidayApplies(@RequestParam(value = "trainerId") int trainerId) {
        return ResponseEntity.ok(holidayService.getConfirmedHolidays(trainerId));
    }

    @PutMapping("/admin/decide")
    public ResponseEntity<?> updateHolidayApplies(@RequestBody AdminDecideHolidayAppliesReqDto reqDto) {
        int successCount = 0;
        successCount = holidayService.editHolidayApplies(reqDto);
        if(successCount == 0) {
            ResponseEntity.badRequest().body(reqDto);
        }
        return ResponseEntity.ok(successCount);
    }
}
