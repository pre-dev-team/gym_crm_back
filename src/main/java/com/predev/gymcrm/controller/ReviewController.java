package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.ReviewReqDto;
import com.predev.gymcrm.dto.resp.ReviewRespDto;
import com.predev.gymcrm.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review") // 임시로 auth로 했습니다
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 트레이너에 대한 리뷰 작성 API
    @PostMapping("/user/make")
    public ResponseEntity<?> writeReview(@RequestBody ReviewReqDto reviewReqDto) {
        // ReviewService를 사용하여 리뷰 작성 기능 호출

        reviewService.writeReview(reviewReqDto);
        return ResponseEntity.created(null).body(201);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllTrainerReviews() {
        List<ReviewRespDto> allReviews = reviewService.findAllTrainerReviews();
        return ResponseEntity.ok(allReviews);
    }

    @GetMapping("/user")
    public ResponseEntity<?> findUserReviews(@RequestParam(value = "accountId") int accountId) {
        return ResponseEntity.ok(reviewService.searchAllUserReviews(accountId));
    }

    // 특정 트레이너의 리뷰 조회 API
    @GetMapping("/{trainerId}/reviews")
    public ResponseEntity<?> findReviewsByTrainerId(@PathVariable int trainerId) {
        List<ReviewRespDto> reviews = reviewService.findReviewsByTrainerId(trainerId);
        return ResponseEntity.ok(reviews);
    }

}