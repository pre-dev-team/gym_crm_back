package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.MakeReservationReqDto;
import com.predev.gymcrm.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservation")
    public ResponseEntity<?> makeReservation(@RequestBody MakeReservationReqDto reqDto) {
        System.out.println(reqDto);
        return ResponseEntity.ok("test");
    }

}