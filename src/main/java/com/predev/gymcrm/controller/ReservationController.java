package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.MakeReservationReqDto;
import com.predev.gymcrm.dto.req.SearchDayReservationReqDto;
import com.predev.gymcrm.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/user/make")
    public ResponseEntity<?> makeReservation(@RequestBody MakeReservationReqDto reqDto) {
        reservationService.insertReservation(reqDto);
        return ResponseEntity.ok(null);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllReservation() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/day")
    public ResponseEntity<?> getDayReservations(SearchDayReservationReqDto reqDto) {
        reservationService.SearchDayReservation(reqDto);
        return ResponseEntity.ok(null);
    }


//    @GetMapping("{userId}")
//    public ResponseEntity<?> getUserId(@PathVariable int userId) {
//        return ResponseEntity.ok(reservationService.findReservationByUserId(userId));
//    }
}
