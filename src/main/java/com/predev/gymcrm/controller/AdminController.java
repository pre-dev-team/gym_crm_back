package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.AdminDecideHolidayAppliesReqDto;
import com.predev.gymcrm.dto.req.AdminSearchReservationCountReqDto;
import com.predev.gymcrm.dto.req.AdminSearchReservationReqDto;
import com.predev.gymcrm.service.AdminService;
import com.predev.gymcrm.service.HolidayService;
import com.predev.gymcrm.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsersByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(adminService.SearchUsersByName(name));
    }

    @GetMapping("/trainers")
    public ResponseEntity<?> getTrainers() {
        return ResponseEntity.ok(adminService.SearchTrainers());
    }

    @GetMapping("/reservations")
    public ResponseEntity<?> searchReservations(AdminSearchReservationReqDto reqDto) {
        return ResponseEntity.ok(adminService.SearchReservations(reqDto));
    }
    @GetMapping("reservations/month/count")
    public ResponseEntity<?> searchReservationCount(AdminSearchReservationCountReqDto reqDto) {
        return ResponseEntity.ok(adminService.findEachTrainerReservationCount(reqDto));
    }

    @GetMapping("/holidays/unconfirmed")
    public ResponseEntity<?> getUnconfirmedHolidayApplies(@RequestParam(value = "trainerId") int trainerId) {
        return ResponseEntity.ok(holidayService.getUnconfirmedHolidays(trainerId));
    }

    @GetMapping("/holidays/confirmed")
    public ResponseEntity<?> getConfirmedHolidayApplies(@RequestParam(value = "trainerId") int trainerId) {
        return ResponseEntity.ok(holidayService.getConfirmedHolidays(trainerId));
    }

    @PutMapping("/holidays/decide")
    public ResponseEntity<?> decidedHolidayApplies(@RequestBody AdminDecideHolidayAppliesReqDto reqDto) {
        int successCount = 0;
        successCount = holidayService.decideHolidayApplies(reqDto);
        if(successCount == 0) {
            ResponseEntity.badRequest().body(reqDto);
        }
        return ResponseEntity.ok(successCount);
    }

}
