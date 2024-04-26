package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.InbodyReqDto;
import com.predev.gymcrm.dto.resp.InbodyRespDto;
import com.predev.gymcrm.dto.resp.SearchInbodyRespDto;
import com.predev.gymcrm.service.InbodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// InbodyController.java

import com.predev.gymcrm.dto.req.InbodyReqDto;
import com.predev.gymcrm.dto.resp.InbodyRespDto;
import com.predev.gymcrm.service.InbodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inbody")
public class InbodyController {

    private final InbodyService inbodyService;

    @Autowired
    public InbodyController(InbodyService inbodyService) {
        this.inbodyService = inbodyService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addInbody(@RequestBody InbodyReqDto inbodyReqDto) {
        return ResponseEntity.ok(inbodyService.addInbody(inbodyReqDto));
    }

    // 각 회원의 userId를 기반으로 Inbody 정보를 가져오는 엔드포인트 추가
    @GetMapping("/account")
    public ResponseEntity<?> getInbodyByAccountId(int accountId) {
        System.out.println("Received userId: " + accountId); // userId 로깅 추가
        List<InbodyRespDto> inbodyRespDtoList = inbodyService.getInbodyByUserId(accountId);
        return ResponseEntity.ok(inbodyRespDtoList);
    }

    @GetMapping("/user/information")
    public ResponseEntity<?> getInbodyInformation(@RequestParam(value = "userId") int userId) {
        return ResponseEntity.ok(inbodyService.getInbodyInformation(userId));
    }

}
