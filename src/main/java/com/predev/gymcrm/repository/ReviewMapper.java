package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.TopRatedReview;
import com.predev.gymcrm.entity.TrainerReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<TrainerReview> findReviewsByTrainerId(int trainerId);
    List<TrainerReview> findReviewsByUserAccountId(int userAccountId);
    int insertTrainerReview(TrainerReview trainerReview);
    Double findAvgReviewScoreByTrainerId(int trainerId);
    // 상위 3명의 트레이너 ID를 조회하는 메서드
    List<TopRatedReview> findTopRatedReviews();
}
