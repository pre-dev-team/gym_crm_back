package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.ReviewReqDto;
import com.predev.gymcrm.dto.resp.ReviewRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.TrainerReview;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private AuthMapper authMapper;

    // 리뷰 작성 기능을 수행하는 메서드
    public void writeReview(ReviewReqDto reqDto) {
        int userId = authMapper.findUserIdByAccountId(reqDto.getAccountId());
        TrainerReview trainerReview = reqDto.toEntity();
        trainerReview.setUserId(userId);
        reviewMapper.insertTrainerReview(trainerReview);
        // 생성된 리뷰 정보를 ReviewRespDto로 변환하여 반환
    }

    // 모든 리뷰를 조회하는 메서드
    public List<ReviewRespDto> findAllTrainerReviews() {
        List<TrainerReview> trainerReviews = reviewMapper.findAllTrainerReviews();
        return trainerReviews.stream()
                .map(TrainerReview::toReviewRespDto)
                .collect(Collectors.toList());
    }

    public List<ReviewRespDto> searchAllUserReviews(int accountId) {
        int userId = authMapper.findUserIdByAccountId(accountId);
        return reviewMapper.findReviewsByUserId(userId).stream().map(review ->
             ReviewRespDto.builder()
                     .trainerReviewId(review.getTrainerReviewId())
                     .reviewText(review.getTrainerReviewText())
                     .reviewScore(review.getTrainerReviewScore())
                     .trainerId(review.getTrainerId())
                     .createDate(review.getCreateDate())
                     .build()
        ).collect(Collectors.toList());
    }

    public List<ReviewRespDto> findReviewsByTrainerId(int trainerId) {

        List<TrainerReview> trainerReviews = reviewMapper.findReviewsByTrainerId(trainerId);
        List<ReviewRespDto> respDtoList = null;
        respDtoList = trainerReviews.stream().map(trainerReview -> {
            int userId = trainerReview.getUserId();
            Account userAccount = authMapper.findAccountByUserId(userId);
            Account trainerAccount = authMapper.findAccountByTrainerId(trainerId);
            return ReviewRespDto.builder()
                    .trainerReviewId(trainerReview.getTrainerReviewId())
                    .trainerId(trainerReview.getTrainerId())
                    .trainerName(trainerAccount.getName())
                    .userId(trainerReview.getUserId())
                    .reviewText(trainerReview.getTrainerReviewText())
                    .reviewScore(trainerReview.getTrainerReviewScore())
                    .username(userAccount.getUsername())
                    .createDate(trainerReview.getCreateDate())
                    .build();
        }).collect(Collectors.toList());

        return respDtoList;
    }
}



