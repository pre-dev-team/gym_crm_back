package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.AccountSigninReqDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.exception.SaveException;
import com.predev.gymcrm.jwt.JwtProvider;
import com.predev.gymcrm.dto.req.AccountSignupReqDto;
import com.predev.gymcrm.repository.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public boolean isDuplicatedUsername(String username) {
        return authMapper.findAccountByUsername(username) != null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void userSignup(AccountSignupReqDto reqDto) {
        int successCount = 0;

        Account account = reqDto.toEntity(passwordEncoder);

        successCount += authMapper.saveAccount(1,account);
        successCount += authMapper.saveUser(account.getAccountId());

        if(successCount < 2) {
            throw new SaveException();
        }
    }


    public String userSignin(AccountSigninReqDto reqDto) {
        Account account = authMapper.findAccountByUsername(reqDto.getUsername());

        if(account == null ) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }
        if (!passwordEncoder.matches(reqDto.getPassword(), account.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }

        return jwtProvider.generateJwt(account);
    }



}
