package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.ReviewReqDto;
import com.predev.gymcrm.dto.req.SearchUserReviewReqDto;
import com.predev.gymcrm.dto.resp.ReviewRespDto;
import com.predev.gymcrm.dto.resp.TopRatedReviewsRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.TopRatedReview;
import com.predev.gymcrm.entity.Trainer;
import com.predev.gymcrm.entity.TrainerReview;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.TimeMapper;
import com.predev.gymcrm.repository.ReviewMapper;
import com.predev.gymcrm.repository.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // 리뷰 작성 기능을 수행하는 메서드
    public int insertReview(ReviewReqDto reqDto) {
        int userId = authMapper.findUserIdByAccountId(reqDto.getAccountId());
        TrainerReview trainerReview = reqDto.toEntity();
        trainerReview.setUserId(userId);
        return reviewMapper.insertTrainerReview(trainerReview);
        // 생성된 리뷰 정보를 ReviewRespDto로 변환하여 반환
    }
    // 모든 리뷰를 조회하는 메서드
    public List<ReviewRespDto> searchAllUserReviews(SearchUserReviewReqDto reqDto) {
        System.out.println(reqDto.getUserId());
        List<TrainerReview> reviewsByUserAccountId = reviewMapper.findReviewsByUserAccountId(reqDto.getUserId());
        return reviewsByUserAccountId.stream().map(TrainerReview::toReviewRespDto).collect(Collectors.toList());
    }
    public List<TopRatedReviewsRespDto> searchTopRatedReviews() {
        List<TopRatedReview> topRatedReviews = reviewMapper.findTopRatedReviews();
        return topRatedReviews.stream().map(TopRatedReview::toRespDto).collect(Collectors.toList());
    }
}


