package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.AccountSigninReqDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.exception.SaveException;
import com.predev.gymcrm.jwt.JwtProvider;
import com.predev.gymcrm.dto.req.AccountSignupReqDto;
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
    public void userSignup(AccountSignupReqDto accountSignupReqDto) {
        int successCount = 0;

        Account account = accountSignupReqDto.toEntity(passwordEncoder);

        successCount += userMapper.saveUser(account);
        successCount += userMapper.saveRole(account.getUserId(), 1);

        if(successCount < 2) {
            throw new SaveException();
        }
    }


    public String userSignin(AccountSigninReqDto accountSigninReqDto) {
        Account account = userMapper.findUserByUsername(accountSigninReqDto.getUserUsername());
        System.out.println(account);
        if(account == null ) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }
        if (!passwordEncoder.matches(accountSigninReqDto.getUserPassword(), account.getUserPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }

        return jwtProvider.generateJwt(account);
    }



}
