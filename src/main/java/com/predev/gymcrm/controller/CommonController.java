package com.predev.gymcrm.controller;
import com.predev.gymcrm.dto.req.TimeReqDto;
import com.predev.gymcrm.dto.resp.TimeRespDto;
import com.predev.gymcrm.entity.Trainer;
import com.predev.gymcrm.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/auth") // 임시로 해두겠습니다
public class CommonController {
    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/time-periods")
    public ResponseEntity<?> getTimePeriods() {
        List<TimeRespDto> timePeriods = commonService.getTimes();
        return ResponseEntity.ok(timePeriods);
    }

    @GetMapping("/view/trainers")
    public ResponseEntity<?> getAllTrainers() {
        List<Trainer> trainers = commonService.findAllTrainers();
        return ResponseEntity.ok(trainers);
    }

}