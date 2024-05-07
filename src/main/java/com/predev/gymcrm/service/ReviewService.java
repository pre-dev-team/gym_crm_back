package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.UserAddReviewReqDto;
import com.predev.gymcrm.dto.req.UserSearchReviewReqDto;
import com.predev.gymcrm.dto.resp.SearchReviewRespDto;
import com.predev.gymcrm.dto.resp.TopRatedReviewsRespDto;
import com.predev.gymcrm.entity.TopRatedReview;
import com.predev.gymcrm.entity.TrainerReview;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private AuthMapper authMapper;

    public int insertReview(UserAddReviewReqDto reqDto) {
        int userId = authMapper.findUserIdByAccountId(reqDto.getAccountId());
        TrainerReview trainerReview = reqDto.toEntity();
        trainerReview.setUserId(userId);
        return reviewMapper.insertTrainerReview(trainerReview);
    }
    public List<SearchReviewRespDto> searchAllUserReviews(UserSearchReviewReqDto reqDto) {
        List<TrainerReview> trainerReviews = new ArrayList<>();
        if(reqDto.getAccountId() == 0) {
            trainerReviews = reviewMapper.findReviewsByUserId(reqDto.getUserId());
        } else if(reqDto.getUserId() == 0){
            trainerReviews = reviewMapper.findReviewsByUserAccountId(reqDto.getAccountId());
        }
        return trainerReviews.stream().map(TrainerReview::toReviewRespDto).collect(Collectors.toList());
    }
    public List<TopRatedReviewsRespDto> searchTopRatedReviews() {
        List<TopRatedReview> topRatedReviews = reviewMapper.findTopRatedReviews();
        return topRatedReviews.stream().map(TopRatedReview::toRespDto).collect(Collectors.toList());
    }
}


