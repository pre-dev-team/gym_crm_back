package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.ReviewReqDto;
import com.predev.gymcrm.dto.resp.ReviewRespDto;
import com.predev.gymcrm.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer") // 임시로 auth로 했습니다
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 트레이너에 대한 리뷰 작성 API
    @PostMapping("/trainerreview")
    public ResponseEntity<?> writeReview(@RequestBody ReviewReqDto reviewReqDto) {
        // ReviewService를 사용하여 리뷰 작성 기능 호출
        ReviewRespDto reviewRespDto = reviewService.writeReview(reviewReqDto);
        System.out.println("Review request received: " + reviewReqDto); // 리뷰 요청 정보 출력
        return ResponseEntity.ok(reviewRespDto);
    }


    @GetMapping("/all")
    public ResponseEntity<List<ReviewRespDto>> findAllTrainerReviews() {
        List<ReviewRespDto> allReviews = reviewService.findAllTrainerReviews();
        return ResponseEntity.ok(allReviews);
    }

    // 특정 트레이너의 리뷰 조회 API
    @GetMapping("/{trainerId}/reviews")
    public ResponseEntity<List<ReviewRespDto>> findReviewsByTrainerId(@PathVariable int trainerId) {
        List<ReviewRespDto> reviews = reviewService.findReviewsByTrainerId(trainerId);
        return ResponseEntity.ok(reviews);
    }

}