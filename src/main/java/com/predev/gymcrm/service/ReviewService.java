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
    public ReviewRespDto writeReview(ReviewReqDto reqDto) {

        int trainerId = reqDto.getTrainerId();
        int userId = reqDto.getUserId();
        String reviewText = reqDto.getReviewText();
        int reviewScore = reqDto.getReviewScore();

        // TrainerReview 객체 생성
        TrainerReview trainerReview = TrainerReview.builder()
                .trainerId(trainerId)
                .userId(userId)
                .trainerReviewText(reviewText)
                .trainerReviewScore(reviewScore)
                .build();

        // 리뷰를 데이터베이스에 저장
        reviewMapper.insertTrainerReview(trainerReview);

        // 생성된 리뷰 정보를 ReviewRespDto로 변환하여 반환
        return trainerReview.toReviewRespDto();
    }

    // 모든 리뷰를 조회하는 메서드
    public List<ReviewRespDto> findAllTrainerReviews() {
        List<TrainerReview> trainerReviews = reviewMapper.findAllTrainerReviews();
        return trainerReviews.stream()
                .map(TrainerReview::toReviewRespDto)
                .collect(Collectors.toList());
    }


    // 특정 트레이너의 리뷰를 조회하는 메서드
    // findReviewsByTrainerId 메서드 수정
//    public List<ReviewRespDto> findReviewsByTrainerId(int trainerId) {
//        List<TrainerReview> trainerReviews = reviewMapper.findReviewsByTrainerId(trainerId);
//        return trainerReviews.stream()
//                .map(TrainerReview::toReviewRespDto)
//                .collect(Collectors.toList());

    public List<ReviewRespDto> findReviewsByTrainerId(int trainerId) {

        List<TrainerReview> trainerReviews = reviewMapper.findReviewsByTrainerId(trainerId);
        List<ReviewRespDto> respDtoList = null;
        respDtoList = trainerReviews.stream().map(trainerReview -> {
            int userId = trainerReview.getUserId();
            Account userAccount = authMapper.findAccountByUserId(userId);
            return ReviewRespDto.builder()
                    .trainerReviewId(trainerReview.getTrainerReviewId())
                    .trainerId(trainerReview.getTrainerId())
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



