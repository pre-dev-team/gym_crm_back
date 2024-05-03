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

    @PostMapping("/user/make")
    public ResponseEntity<?> addReview(@RequestBody ReviewReqDto reviewReqDto) {
        return ResponseEntity.created(null).body(reviewService.insertReview(reviewReqDto));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserReviews(SearchUserReviewReqDto reqDto) {
        return ResponseEntity.ok(reviewService.searchAllUserReviews(reqDto));
    }
    
    @GetMapping("/toprated")
    public ResponseEntity<?> getTopRatedTrainersInformation() {
        return ResponseEntity.ok(reviewService.searchTopRatedReviews());
    }
}
