package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.AdminDecideHolidayAppliesReqDto;
import com.predev.gymcrm.dto.req.AdminPasswordChangeReqDto;
import com.predev.gymcrm.dto.req.AdminSearchReservationReqDto;
import com.predev.gymcrm.service.AccountService;
import com.predev.gymcrm.service.AdminService;
import com.predev.gymcrm.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsersByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(adminService.SearchUsersByName(name));
    }

    @GetMapping("/reservations")
    public ResponseEntity<?> searchReservations(AdminSearchReservationReqDto reqDto) {
        return ResponseEntity.ok(adminService.SearchReservations(reqDto));
    }

    @GetMapping("/holidays/unconfirmed")
    public ResponseEntity<?> getUnconfirmedHolidayApplies(@RequestParam(value = "trainerId") int trainerId) {
        return ResponseEntity.ok(holidayService.selectUnconfirmedHolidays(trainerId));
    }

    @GetMapping("/holidays/confirmed")
    public ResponseEntity<?> getConfirmedHolidayApplies(@RequestParam(value = "trainerId") int trainerId) {
        return ResponseEntity.ok(holidayService.selectConfirmedHolidays(trainerId));
    }

    @PutMapping("/holidays/decide")
    public ResponseEntity<?> decidedHolidayApplies(@RequestBody AdminDecideHolidayAppliesReqDto reqDto) {
        int successCount = 0;
        successCount = holidayService.updateHolidayApplies(reqDto);
        if(successCount == 0) {
            ResponseEntity.badRequest().body(reqDto);
        }
        return ResponseEntity.ok(successCount);
    }

    @GetMapping("/reservations/month/count")
    public ResponseEntity<?> getWeeklyTrainerReservationCounts() {
        return ResponseEntity.ok(adminService.getWeeklyTrainerReservationCounts());
    }

    @PutMapping("/edit/password")
    public ResponseEntity<?> changeAdminPassword(@RequestBody AdminPasswordChangeReqDto reqDto) {
        return ResponseEntity.ok(accountService.editAdminPassword(reqDto));
    }
}