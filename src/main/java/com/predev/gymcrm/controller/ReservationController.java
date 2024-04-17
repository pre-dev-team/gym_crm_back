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
        // 특정 트레이너의 예약 정보를 가져오는 요청
        System.out.println(reqDto.getTrainerId());
        System.out.println(reqDto.getToday());

        List<MyTodayScheduleRespDto> reservations = reservationService.getTodayReservation(reqDto);

        System.out.println(reservations);

        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/trainer/searchall")
    public ResponseEntity<?> getSearchAllUser(SearchReservationUserReqDto reqDto) {
        return ResponseEntity.ok(reservationService.searchReservationsUser(reqDto));
    }
}