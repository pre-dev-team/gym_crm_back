package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.SearchUserReqDto;
import com.predev.gymcrm.entity.TrainerReview;
import com.predev.gymcrm.entity.User;
import com.predev.gymcrm.repository.ReviewMapper;
import com.predev.gymcrm.repository.TrainerMapper;
import com.predev.gymcrm.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private TrainerMapper trainerMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    public List<TrainerReview> findAllTrainerReviews() {
        return reviewMapper.findAllTrainerReviews();
    }

//    public List<SearchUserReqDto> searchUsers(SearchUserReqDto searchUserReqDto) {
//        int startIndex = (searchUserReqDto.getPage() - 1) * searchUserReqDto.getCount();
//
////        List<User> users = userMapper.findUsers(
////                startIndex,
////                searchUserReqDto.getCount(),
////                searchUserReqDto.getUserId()
////        );
//
//        return users.stream().map(User::toSearchUserReqDto).collect(Collectors.toList());
//    }
}
