package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.AdminDecideHolidayAppliesReqDto;
import com.predev.gymcrm.dto.req.TrainerDeleteHolidayReqDto;
import com.predev.gymcrm.dto.req.TrainerAddHolidayReqDto;
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
    public ResponseEntity<?> addTrainerHoliday(@RequestBody TrainerAddHolidayReqDto trainerAddHolidayReqDto) {
        holidayService.insertTrainerHoliday(trainerAddHolidayReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHoliday(@RequestBody TrainerDeleteHolidayReqDto trainerDeleteHolidayReqDto) {
        holidayService.deleteHoliday(trainerDeleteHolidayReqDto);
        return ResponseEntity.ok(trainerDeleteHolidayReqDto);
    }

    @GetMapping("/select")
    public ResponseEntity<?> getHolidays(@RequestParam(value = "accountId") int accountId) {
        return ResponseEntity.ok(holidayService.searchHoliday(accountId));
    }

    @GetMapping("/find")
    public ResponseEntity<?> getHolidayTimeIds(@RequestParam int trainerId, @RequestParam String holidayDate) {
        return ResponseEntity.ok(holidayService.searchHolidaytimeIdsByTrainerIdAndHolidayDate(trainerId,holidayDate));
    }

    @GetMapping("/admin/unconfirmed")
    public ResponseEntity<?> getUnconfirmedHolidayApplies(@RequestParam(value = "trainerId") int trainerId) {
        return ResponseEntity.ok(holidayService.searchUnconfirmedHolidays(trainerId));
    }

    @GetMapping("/admin/confirmed")
    public ResponseEntity<?> getConfirmedHolidayApplies(@RequestParam(value = "trainerId") int trainerId) {
        return ResponseEntity.ok(holidayService.searchConfirmedHolidays(trainerId));
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
