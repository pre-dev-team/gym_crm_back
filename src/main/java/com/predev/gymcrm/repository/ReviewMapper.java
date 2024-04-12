package com.predev.gymcrm.repository;

import com.predev.gymcrm.dto.req.ReviewReqDto;
import com.predev.gymcrm.entity.TrainerReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ReviewMapper {
    public List<TrainerReview> findAllTrainerReviews();

    // 특정 트레이너의 리뷰 조회 쿼리
    List<TrainerReview> findReviewsByTrainerId(int trainerId);
    List<TrainerReview> findReviewsByUserId(int userId);
    void insertTrainerReview(TrainerReview trainerReview);
    Double findAvgReviewScoreByTrainerId(int trainerId);
}
