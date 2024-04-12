package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.ReviewReqDto;
import com.predev.gymcrm.dto.resp.ReviewRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.Trainer;
import com.predev.gymcrm.entity.TrainerReview;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.CommonMapper;
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
    private CommonMapper commonMapper;

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

        // 리뷰에 포함된 모든 트레이너 ID를 수집합니다.
        Set<Integer> trainerIds = trainerReviews.stream()
                .map(TrainerReview::getTrainerId)
                .collect(Collectors.toSet());

        // 각 트레이너 ID에 해당하는 이미지 URL을 가져옵니다.
        Map<Integer, String> trainerProfileImgUrls = commonMapper.getTrainers().stream()
                .filter(trainer -> trainerIds.contains(trainer.getTrainerId()))
                .collect(Collectors.toMap(Trainer::getTrainerId, Trainer::getTrainerProfileImgUrl));

        // ReviewRespDto 리스트를 생성합니다.
        List<ReviewRespDto> respDtoList = trainerReviews.stream()
                .map(trainerReview -> {
                    int trainerId = trainerReview.getTrainerId();
                    Account userAccount = authMapper.findAccountByUserId(trainerReview.getUserId());
                    Account trainerAccount = authMapper.findAccountByTrainerId(trainerId);
                    String trainerProfileImgUrl = trainerProfileImgUrls.get(trainerId);

                    return ReviewRespDto.builder()
                            .trainerReviewId(trainerReview.getTrainerReviewId())
                            .trainerId(trainerId)
                            .trainerName(trainerAccount.getName())
                            .userId(trainerReview.getUserId())
                            .reviewText(trainerReview.getTrainerReviewText())
                            .reviewScore(trainerReview.getTrainerReviewScore())
                            .username(userAccount.getUsername())
                            .createDate(trainerReview.getCreateDate())
                            .trainerProfileImgUrl(trainerProfileImgUrl)
                            .build();
                })
                .collect(Collectors.toList());

        return respDtoList;
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
        List<ReviewRespDto> respDtoList = trainerReviews.stream().map(trainerReview -> {
            int userId = trainerReview.getUserId();
            Account userAccount = authMapper.findAccountByUserId(userId);
            Account trainerAccount = authMapper.findAccountByTrainerId(trainerId);
            Map<Integer, String> trainerProfileImgUrls = commonMapper.getTrainers().stream()
                    .collect(Collectors.toMap(Trainer::getTrainerId, Trainer::getTrainerProfileImgUrl));

            String trainerProfileImgUrl = trainerProfileImgUrls.get(trainerId);

            return ReviewRespDto.builder()
                    .trainerReviewId(trainerReview.getTrainerReviewId())
                    .trainerId(trainerReview.getTrainerId())
                    .trainerName(trainerAccount.getName())
                    .userId(trainerReview.getUserId())
                    .reviewText(trainerReview.getTrainerReviewText())
                    .reviewScore(trainerReview.getTrainerReviewScore())
                    .username(userAccount.getUsername())
                    .createDate(trainerReview.getCreateDate())
                    .trainerProfileImgUrl(trainerProfileImgUrl)
                    .build();
        }).collect(Collectors.toList());

        return respDtoList;
    }

    public List<ReviewRespDto> getTopRatedTrainersInformation() {
        // 상위 3명의 트레이너 ID 조회
        List<Integer> topRatedTrainers = reviewMapper.findTopRatedTrainers();

        // 상위 트레이너들의 정보를 담을 리스트 초기화
        List<ReviewRespDto> topRatedTrainersInfo = new ArrayList<>();

        // 각 상위 트레이너에 대해 가장 높은 점수를 가진 리뷰를 조회하여 리스트에 추가
        for (Integer trainerId : topRatedTrainers) {
            // 상위 트레이너의 정보 조회
            Account trainerAccount = authMapper.findAccountByTrainerId(trainerId);

            // 트레이너의 가장 높은 점수를 가진 리뷰 조회
            TrainerReview topReview = reviewMapper.findTopReviewByTrainerId(trainerId);

            if (topReview != null) {
                // 트레이너의 프로필 이미지 URL 조회
                String trainerProfileImgUrl = commonMapper.getTrainerProfileImgUrl(trainerId);

                // ReviewRespDto 객체 생성 및 리스트에 추가
                ReviewRespDto reviewRespDto = ReviewRespDto.builder()
                        .trainerReviewId(topReview.getTrainerReviewId())
                        .trainerId(topReview.getTrainerId())
                        .trainerName(trainerAccount.getName())
                        .userId(topReview.getUserId())
                        .reviewText(topReview.getTrainerReviewText())
                        .reviewScore(topReview.getTrainerReviewScore())
                        .username(trainerAccount.getUsername())
                        .createDate(topReview.getCreateDate())
                        .trainerProfileImgUrl(trainerProfileImgUrl)
                        .build();

                topRatedTrainersInfo.add(reviewRespDto);
            } else {
                // 가장 높은 점수를 가진 리뷰가 없는 경우에는 트레이너의 정보만 추가
                ReviewRespDto reviewRespDto = ReviewRespDto.builder()
                        .trainerId(trainerId)
                        .trainerName(trainerAccount.getName())
                        .trainerProfileImgUrl(commonMapper.getTrainerProfileImgUrl(trainerId))
                        .build();

                topRatedTrainersInfo.add(reviewRespDto);
            }
        }

        return topRatedTrainersInfo;
    }
}



//    public List<ReviewRespDto> findReviewsByTrainerId(int trainerId) {
//
//        List<TrainerReview> trainerReviews = reviewMapper.findReviewsByTrainerId(trainerId);
//        List<ReviewRespDto> respDtoList = trainerReviews.stream().map(trainerReview -> {
//            int userId = trainerReview.getUserId();
//            Account userAccount = authMapper.findAccountByUserId(userId);
//            Account trainerAccount = authMapper.findAccountByTrainerId(trainerId);
//
//            // 추가: Trainer 정보 가져오기
//            Trainer trainer = commonMapper.findTrainerByTrainerId(trainerId);
//            String trainerProfileImgUrl = trainer.getTrainerProfileImgUrl();
//
//            return ReviewRespDto.builder()
//                    .trainerReviewId(trainerReview.getTrainerReviewId())
//                    .trainerId(trainerReview.getTrainerId())
//                    .trainerName(trainerAccount.getName())
//                    .userId(trainerReview.getUserId())
//                    .reviewText(trainerReview.getTrainerReviewText())
//                    .reviewScore(trainerReview.getTrainerReviewScore())
//                    .username(userAccount.getUsername())
//                    .createDate(trainerReview.getCreateDate())
//                    .trainerProfileImgUrl(trainerProfileImgUrl)
//                    .build();
//        }).collect(Collectors.toList());
//
//        return respDtoList;
//    }




