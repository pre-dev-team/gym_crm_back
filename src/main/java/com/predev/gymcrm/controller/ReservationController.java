package com.predev.gymcrm.controller;

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

    @GetMapping("/all")
    public ResponseEntity<?> getAllReservation() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/day")
    public ResponseEntity<?> getDayReservations(SearchDayReservationReqDto reqDto) {
        String a = LocalDateTime.parse(reqDto.getDate(), DateTimeFormatter.ISO_DATE_TIME).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(a);
        return ResponseEntity.ok(null);
    }


//    @GetMapping("{userId}")
//    public ResponseEntity<?> getUserId(@PathVariable int userId) {
//        return ResponseEntity.ok(reservationService.findReservationByUserId(userId));
//    }
}
