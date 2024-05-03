package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.ReviewReqDto;
import com.predev.gymcrm.dto.req.SearchUserReviewReqDto;
import com.predev.gymcrm.dto.resp.ReviewRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.Trainer;
import com.predev.gymcrm.entity.TrainerReview;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.TimeMapper;
import com.predev.gymcrm.repository.ReviewMapper;
import com.predev.gymcrm.repository.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private TrainerMapper trainerMapper;

    @Autowired
    private TimeMapper timeMapper;

    // 리뷰 작성 기능을 수행하는 메서드
    public void writeReview(ReviewReqDto reqDto) {
        int userId = authMapper.findUserIdByAccountId(reqDto.getAccountId());
        TrainerReview trainerReview = reqDto.toEntity();
        trainerReview.setUserId(userId);
        reviewMapper.insertTrainerReview(trainerReview);
    }

    public List<ReviewRespDto> searchAllUserReviews(SearchUserReviewReqDto reqDto) {
        int userId = 0;
        if (reqDto.getUserId() == 0) {
            userId = authMapper.findUserIdByAccountId(reqDto.getAccountId());
            reqDto.setUserId(userId);
        }
        userId = reqDto.getUserId();
        return reviewMapper.findReviewsByUserId(userId).stream().map(review ->
                ReviewRespDto.builder()
                        .trainerReviewId(review.getTrainerReviewId())
                        .reviewText(review.getTrainerReviewText())
                        .reviewScore(review.getTrainerReviewScore())
                        .trainerId(review.getTrainerId())
                        .createDate(review.getCreateDate())
                        .trainerName(review.getTrainer().getAccount().getName())
                        .build()
        ).collect(Collectors.toList());
    }

}




