package com.predev.gymcrm.repository;

import com.predev.gymcrm.dto.req.ReviewReqDto;
import com.predev.gymcrm.entity.TrainerReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<TrainerReview> findAllTrainerReviews();

    // 특정 트레이너의 리뷰 조회 쿼리
    public List<TrainerReview> findReviewsByTrainerId(int trainerId);
  
    List<TrainerReview> findReviewsByUserId(int userId);

    void insertTrainerReview(TrainerReview trainerReview);

    // 상위 3명의 트레이너 ID를 조회하는 메서드
    List<Integer> findTopRatedTrainers();

    // 각 트레이너들의 평균 점수를 조회하는 메서드
    Double findAverageRatingByTrainer(int trainerId);

    // 리뷰 갯수가 많은 3명의 트레이너들 중에서 각 트레이너마다 평점이 높은 리뷰 하나를 선정하는 메서드
    List<TrainerReview> findTopReviewAmongTopTrainers(int trainerId);

    TrainerReview findTopReviewByTrainerId(int trainerId);
}
