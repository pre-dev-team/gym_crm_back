package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.UserSignupReqDto;
import com.predev.gymcrm.entity.User;
import com.predev.gymcrm.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;

    public void userSignup(UserSignupReqDto userSignupReqDto) {
        userMapper.saveUser(userSignupReqDto.toEntity());

    }
}
