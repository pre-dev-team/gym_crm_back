package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.MakeReservationFromUserReqDto;
import com.predev.gymcrm.repository.ReservationMapper;
import com.predev.gymcrm.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("test");
    }

    @PostMapping("/reservation")
    public ResponseEntity<?> makeReservationByUser(@RequestBody MakeReservationFromUserReqDto reqDto) {
        reservationService.saveReservationDate(reqDto);
        return ResponseEntity.ok(null);
    }
}