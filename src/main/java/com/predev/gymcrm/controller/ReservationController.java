package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.*;
import com.predev.gymcrm.dto.resp.MyTodayScheduleRespDto;
import com.predev.gymcrm.dto.resp.SearchReservationUserRespDto;
import com.predev.gymcrm.dto.resp.SelectMyMembersInformationRespDto;
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
    public ResponseEntity<?> getReservation(@RequestBody MakeReservationReqDto reqDto) {
        reservationService.insertReservation(reqDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("user/find")
    public ResponseEntity<?> getUserAllReservation(@RequestParam(value = "accountId") int accountId) {
        return ResponseEntity.ok(reservationService.searchReservationsByUserId(accountId));
    }

    @GetMapping("/day")
    public ResponseEntity<?> getDayReservations(SearchDayReservationReqDto reqDto) {
        return ResponseEntity.ok(reservationService.searchDayReservation(reqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllReservation() {
        return ResponseEntity.ok(reservationService.searchAll());
    }



    @GetMapping("/trainer/schedulefor2days")
    public ResponseEntity<?> getTodayReservation(MyTodayScheduleReqDto reqDto) {
        List<MyTodayScheduleRespDto> reservations = reservationService.searchTodayReservation(reqDto);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/trainer/searchall")
    public ResponseEntity<?> getSearchAllUser(SearchReservationUserReqDto searchReservationUserReqDto) {
        return ResponseEntity.ok(reservationService.searchReservationsUser(searchReservationUserReqDto));
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteReservation(@RequestParam(value = "reservationId") int reservationId) {
        return ResponseEntity.ok(reservationService.deleteReservationByReservationId(reservationId));
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateReservation(@RequestBody EditReservationReqDto reqDto) {
        reservationService.editReservation(reqDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/user/information")
    public ResponseEntity<?> getMymemberInformation(SearchMymembersInformationReqDto searchMymembersInformationReqDto) {
        List<SelectMyMembersInformationRespDto> respDtos = reservationService.selectMymembersInformation(searchMymembersInformationReqDto);
        return ResponseEntity.ok(respDtos);
    }

    @GetMapping("/mypage/members")
    public ResponseEntity<?> getMyMembers(@RequestParam(value = "accountId") int trainerAccountId) {
        return ResponseEntity.ok(reservationService.selectMyMembers(trainerAccountId));

    }

}
