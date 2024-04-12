package com.predev.gymcrm.controller;
import com.predev.gymcrm.dto.resp.TimeRespDto;
import com.predev.gymcrm.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @GetMapping("/duration")
    public ResponseEntity<?> getDuration() {
        List<TimeRespDto> timePeriods = commonService.getTimes();
        return ResponseEntity.ok(timePeriods);
    }



}