package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.ReviewReqDto;
import com.predev.gymcrm.dto.req.SearchUserReviewReqDto;
import com.predev.gymcrm.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review") 
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 트레이너에 대한 리뷰 작성 API
    @PostMapping("/user/make")
    public ResponseEntity<?> addReview(@RequestBody ReviewReqDto reviewReqDto) {
        return ResponseEntity.created(null).body(reviewService.insertReview(reviewReqDto));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserReviews(SearchUserReviewReqDto reqDto) {
        return ResponseEntity.ok(reviewService.searchAllUserReviews(reqDto));
    }
}

    // 상위 3명의 트레이너 정보 및 가장 높은 점수를 가진 리뷰 조회
    @GetMapping("/toprated")
    public ResponseEntity<?> getTopRatedTrainersInformation() {
        return ResponseEntity.ok(reviewService.searchTopRatedReviews());
    }
}
