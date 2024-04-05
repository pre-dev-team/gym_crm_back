package com.predev.gymcrm.repository;





import com.predev.gymcrm.entity.TrainerReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ReviewMapper {
    public List<TrainerReview> findAllTrainerReviews();
}
