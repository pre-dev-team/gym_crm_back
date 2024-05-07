package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.*;
import com.predev.gymcrm.dto.resp.TrainerSearchMyMembersInformationRespDto;
import com.predev.gymcrm.dto.resp.TrainerSearchMyMembersRespDto;
import com.predev.gymcrm.dto.resp.TrainerSearchScheduleRespDto;
import com.predev.gymcrm.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/user/make")
    public ResponseEntity<?> getReservation(@RequestBody UserAddReservationReqDto reqDto) {
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
    public ResponseEntity<?> getTodayReservation(TrainerSearchTodayScheduleReqDto reqDto) {
        List<TrainerSearchScheduleRespDto> reservations = reservationService.searchTodayReservation(reqDto);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/trainer/searchall")
    public ResponseEntity<?> getSearchAllUser(TrainerSearchReservationUserReqDto trainerSearchReservationUserReqDto) {
        return ResponseEntity.ok(reservationService.searchReservationsUser(trainerSearchReservationUserReqDto));
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteReservation(@RequestParam(value = "reservationId") int reservationId) {
        return ResponseEntity.ok(reservationService.deleteReservationByReservationId(reservationId));
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateReservation(@RequestBody UserEditReservationReqDto reqDto) {
        reservationService.editReservation(reqDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/user/information")
    public ResponseEntity<?> getMymemberInformation(TrainerSearchMembersInformationReqDto trainerSearchMembersInformationReqDto) {
        List<TrainerSearchMyMembersInformationRespDto> respDtos = reservationService.searchMymembersInformation(trainerSearchMembersInformationReqDto);
        return ResponseEntity.ok(respDtos);
    }

    @GetMapping("/admin/reservations")
    public ResponseEntity<?> searchReservations(AdminSearchReservationReqDto reqDto) {
        return ResponseEntity.ok(reservationService.searchReservations(reqDto));
    }
    
    @GetMapping("/mypage/members")
    public ResponseEntity<?> getMyMembers(@RequestParam(value = "accountId") int trainerAccountId) {
        return ResponseEntity.ok(reservationService.searchMyMembers(trainerAccountId));

    }

}
