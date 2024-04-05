package com.predev.gymcrm.controller;

import com.predev.gymcrm.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllReservation() {
        return ResponseEntity.ok(reservationService.findAll());
    }

//    @GetMapping("{userId}")
//    public ResponseEntity<?> getUserId(@PathVariable int userId) {
//        return ResponseEntity.ok(reservationService.findReservationByUserId(userId));
//    }
}
