package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.TrainerReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ReviewMapper {
    public List<TrainerReview> findAllTrainerReviews();

    // 특정 트레이너의 리뷰 조회 쿼리
    public List<TrainerReview> findReviewsByTrainerId(int trainerId);

    void insertTrainerReview(TrainerReview trainerReview);

}
