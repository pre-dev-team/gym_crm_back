package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.TrainerSignupReqDto;
import com.predev.gymcrm.dto.req.UserSigninReqDto;
import com.predev.gymcrm.entity.User;
import com.predev.gymcrm.jwt.JwtProvider;
import com.predev.gymcrm.repository.TrainerMapper;
import com.predev.gymcrm.dto.req.UserSignupReqDto;
import com.predev.gymcrm.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TrainerMapper trainerMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProvider jwtProvider;

    public boolean isDuplicatedByUsername(String username) {
        return userMapper.findUserByUsername(username) != null;
    }

    public void userSignup(UserSignupReqDto userSignupReqDto) {
        userMapper.saveUser(userSignupReqDto.toEntity());

    }

    public void trainerSignup(TrainerSignupReqDto trainerSignupReqDto) {
        trainerMapper.saveTrainer(trainerSignupReqDto.toEntity());
    }

    public String userSignin(UserSigninReqDto userSigninReqDto) {
        User user = userMapper.findUserByUsername(userSigninReqDto.getUserUsername());
        System.out.println(user);
        if(user == null ) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }
        return jwtProvider.generateJwt(user);
    }

}
