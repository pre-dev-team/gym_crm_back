package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.UserSigninReqDto;
import com.predev.gymcrm.entity.User;
import com.predev.gymcrm.exception.SaveException;
import com.predev.gymcrm.jwt.JwtProvider;
import com.predev.gymcrm.dto.req.UserSignupReqDto;
import com.predev.gymcrm.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public boolean isDuplicatedByUsername(String username) {
        return userMapper.findUserByUsername(username) != null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void userSignup(UserSignupReqDto userSignupReqDto) {
        int successCount = 0;

        User user = userSignupReqDto.toEntity(passwordEncoder);

        successCount += userMapper.saveUser(user);
        successCount += userMapper.saveRole(user.getUserId(), 1);

        if(successCount < 2) {
            throw new SaveException();
        }
    }


    public String userSignin(UserSigninReqDto userSigninReqDto) {
        User user = userMapper.findUserByUsername(userSigninReqDto.getUserUsername());
        System.out.println(user);
        if(user == null ) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }
        if (!passwordEncoder.matches(userSigninReqDto.getUserPassword(), user.getUserPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }

        return jwtProvider.generateJwt(user);
    }



}
