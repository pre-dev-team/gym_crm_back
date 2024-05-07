package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.TrainerAddInbodyReqDto;
import com.predev.gymcrm.dto.resp.UserSearchInbodyRespDto;
import com.predev.gymcrm.service.InbodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inbody")
public class InbodyController {

    @Autowired
    private InbodyService inbodyService;

    @PostMapping("/add")
    public ResponseEntity<?> addInbody(@RequestBody TrainerAddInbodyReqDto trainerAddInbodyReqDto) {
        return ResponseEntity.ok(inbodyService.insertInbody(trainerAddInbodyReqDto));
    }

    @GetMapping("/account")
    public ResponseEntity<?> getInbodyByAccountId(int accountId) {
        List<UserSearchInbodyRespDto> userSearchInbodyRespDtoList = inbodyService.searchInbodyByUserId(accountId);
        return ResponseEntity.ok(userSearchInbodyRespDtoList);
    }

    @GetMapping("/user/information")
    public ResponseEntity<?> getInbodyInformation(@RequestParam(value = "userId") int userId) {
        return ResponseEntity.ok(inbodyService.searchInbodyInformation(userId));
    }

}
