package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.InbodyReqDto;
import com.predev.gymcrm.dto.resp.InbodyRespDto;
import com.predev.gymcrm.service.InbodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer") //임시로 trainer로 지정해두었습니다.
public class InbodyController {

    private final InbodyService inbodyService;

    @Autowired
    public InbodyController(InbodyService inbodyService) {
        this.inbodyService = inbodyService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addInbody(@RequestBody InbodyReqDto inbodyReqDto) {
        InbodyRespDto inbodyRespDto = inbodyService.addInbody(inbodyReqDto);
        return ResponseEntity.ok(inbodyRespDto);
    }

    @GetMapping("/inbody/{accountId}")
    public ResponseEntity<?> getInbodyByAccountId(@PathVariable int accountId) {
        List<InbodyRespDto> inbodyRespDtoList = inbodyService.getInbodyByAccountId(accountId);
        return ResponseEntity.ok(inbodyRespDtoList);
    }
}