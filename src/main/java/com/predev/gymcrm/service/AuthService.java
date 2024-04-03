package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.TrainerSignupReqDto;
import com.predev.gymcrm.repository.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TrainerMapper trainerMapper;

    public void trainerSignup(TrainerSignupReqDto trainerSignupReqDto) {
        trainerMapper.saveTrainer(trainerSignupReqDto.toEntity());
    }
}
