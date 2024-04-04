package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.SearchTrainerReqDto;
import com.predev.gymcrm.dto.req.SearchUserReqDto;
import com.predev.gymcrm.entity.Trainer;
import com.predev.gymcrm.entity.User;
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

    public List<SearchUserReqDto> searchUsers(SearchUserReqDto searchUserReqDto) {
        int startIndex = (searchUserReqDto.getPage() - 1) * searchUserReqDto.getCount();

        List<User> users = userMapper.findUsers(
                startIndex,
                searchUserReqDto.getCount(),
                searchUserReqDto.getUserId()
        );

        return users.stream().map(User::toSearchUserReqDto).collect(Collectors.toList());
    }

    public List<SearchTrainerReqDto> searchTrainers(SearchTrainerReqDto searchTrainerReqDto) {
        int startIndex = (searchTrainerReqDto.getPage() - 1)* searchTrainerReqDto.getCount();

        List<Trainer> trainers = trainerMapper.findTrainers(
                startIndex,
                searchTrainerReqDto.getCount(),
                searchTrainerReqDto.getTrainerId()
        );
        return trainers.stream().map(Trainer::toSearchTrainerReqDto).collect(Collectors.toList());
    }
}
