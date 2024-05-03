package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.ReviewReqDto;
import com.predev.gymcrm.dto.req.SearchUserReviewReqDto;
import com.predev.gymcrm.dto.resp.ReviewRespDto;
import com.predev.gymcrm.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review") 
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

    @GetMapping("/user")
    public ResponseEntity<?> findUserReviews(SearchUserReviewReqDto reqDto) {
        return ResponseEntity.ok(reviewService.searchAllUserReviews(reqDto));
    }
}

    // 특정 트레이너의 리뷰 조회 API
//    @GetMapping("/{trainerId}/reviews")
//    public ResponseEntity<?> findReviewsByTrainerId(@PathVariable int trainerId) {
//        List<ReviewRespDto> reviews = reviewService.searchReviewsByTrainerId(trainerId);
//        return ResponseEntity.ok(reviews);
//    }

    // 상위 3명의 트레이너 정보 및 가장 높은 점수를 가진 리뷰 조회
//    @GetMapping("/toprated")
//    public ResponseEntity<List<ReviewRespDto>> getTopRatedTrainersInformation() {
//        // ReviewService를 사용하여 상위 3명의 트레이너 정보 및 가장 높은 점수를 가진 리뷰 조회
//        List<ReviewRespDto> topRatedTrainersInfo = reviewService.getTopRatedTrainersInformation();
//        return ResponseEntity.ok(topRatedTrainersInfo);
//    }
//}
