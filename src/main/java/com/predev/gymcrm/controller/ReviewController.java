package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.UserAddReviewReqDto;
import com.predev.gymcrm.dto.req.UserSearchReviewReqDto;
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
    public ResponseEntity<?> addReview(@RequestBody UserAddReviewReqDto userAddReviewReqDto) {
        return ResponseEntity.created(null).body(reviewService.insertReview(userAddReviewReqDto));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserReviews(UserSearchReviewReqDto reqDto) {
        return ResponseEntity.ok(reviewService.searchAllUserReviews(reqDto));
    }
    
    @GetMapping("/toprated")
    public ResponseEntity<?> getTopRatedTrainersInformation() {
        return ResponseEntity.ok(reviewService.searchTopRatedReviews());
    }
}
