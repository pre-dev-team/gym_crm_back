package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.AdminSearchReservationReqDto;
import com.predev.gymcrm.repository.ReservationMapper;
import com.predev.gymcrm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

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


}
