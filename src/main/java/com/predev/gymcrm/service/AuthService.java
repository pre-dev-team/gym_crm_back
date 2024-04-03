package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.TrainerSignupReqDto;
import com.predev.gymcrm.repository.TrainerMapper;
import com.predev.gymcrm.dto.req.UserSignupReqDto;
import com.predev.gymcrm.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TrainerMapper trainerMapper;

    public boolean isDuplicatedByUsername(String username) {
        return userMapper.findUserByUsername(username) != null;
    }

    public void userSignup(UserSignupReqDto userSignupReqDto) {
        userMapper.saveUser(userSignupReqDto.toEntity());
      
    @Autowired
    private UserMapper userMapper;
    public void userSignup(UserSignupReqDto userSignupReqDto) {
        userMapper.saveUser(userSignupReqDto.toEntity());
    }
    public void trainerSignup(TrainerSignupReqDto trainerSignupReqDto) {
        trainerMapper.saveTrainer(trainerSignupReqDto.toEntity());
    }

}
