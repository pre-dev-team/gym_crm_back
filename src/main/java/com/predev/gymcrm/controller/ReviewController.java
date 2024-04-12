package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.ReviewReqDto;
import com.predev.gymcrm.dto.resp.ReviewRespDto;
import com.predev.gymcrm.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/write") // 갑자기 리뷰인서트가 안되기는 하는데 일단 구현하려고 했던 부분을 끝내놨습니다
    public ResponseEntity<?> writeReview(@RequestBody ReviewReqDto reviewReqDto) {
        // ReviewService를 사용하여 리뷰 작성 기능 호출
        ReviewRespDto reviewRespDto = reviewService.writeReview(reviewReqDto);
        System.out.println("Review request received: " + reviewReqDto); // 리뷰 요청 정보 출력
        return ResponseEntity.ok(reviewRespDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewRespDto>> getAllTrainerReviews() {
        List<ReviewRespDto> allReviews = reviewService.findAllTrainerReviews();
        return ResponseEntity.ok(allReviews);
    }

    // 특정 트레이너의 리뷰 조회 API
    @GetMapping("/{trainerId}/reviews")
    public ResponseEntity<List<ReviewRespDto>> getReviewsByTrainerId(@PathVariable int trainerId) {
        List<ReviewRespDto> reviews = reviewService.findReviewsByTrainerId(trainerId);
        return ResponseEntity.ok(reviews);
    }

    // 상위 3명의 트레이너 정보 및 가장 높은 점수를 가진 리뷰 조회
    @GetMapping("/toprated")
    public ResponseEntity<List<ReviewRespDto>> getTopRatedTrainersInformation() {
        // ReviewService를 사용하여 상위 3명의 트레이너 정보 및 가장 높은 점수를 가진 리뷰 조회
        List<ReviewRespDto> topRatedTrainersInfo = reviewService.getTopRatedTrainersInformation();
        return ResponseEntity.ok(topRatedTrainersInfo);
    }
}
