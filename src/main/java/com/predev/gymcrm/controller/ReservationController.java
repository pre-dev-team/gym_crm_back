package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.*;
import com.predev.gymcrm.dto.resp.MyTodayScheduleRespDto;
import com.predev.gymcrm.dto.resp.SearchReservationUserRespDto;
import com.predev.gymcrm.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @GetMapping("user/find")
    public ResponseEntity<?> getUserAllReservation(@RequestParam(value = "accountId") int accountId) {
        return ResponseEntity.ok(reservationService.searchReservationsByUserId(accountId));
    }

    @GetMapping("/day")
    public ResponseEntity<?> getDayReservations(SearchDayReservationReqDto reqDto) {
        return ResponseEntity.ok(reservationService.SearchDayReservation(reqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllReservation() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/trainer/day")
    public ResponseEntity<?> getUnreservedTrainers(SearchUnreservedTrainerReqDto reqDto) {
        return ResponseEntity.ok(reservationService.SearchUnreservedTrainers(reqDto));
    }

    @GetMapping("/trainer/schedulefor2days")
    public ResponseEntity<?> getTodayReservation(MyTodayScheduleReqDto reqDto) {
        List<MyTodayScheduleRespDto> reservations = reservationService.getTodayReservation(reqDto);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/trainer/searchall")
    public ResponseEntity<?> getSearchAllUser(SearchReservationUserReqDto searchReservationUserReqDto) {
        return ResponseEntity.ok(reservationService.searchReservationsUser(searchReservationUserReqDto));
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteReservation(@RequestParam(value = "reservationId") int reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservationByReservationId(reservationId));
    }

    @PutMapping("/user")
    public ResponseEntity<?> editReservation(@RequestBody EditReservationReqDto reqDto) {
        reservationService.updateReservation(reqDto);
        return ResponseEntity.ok(null);
    }
}
