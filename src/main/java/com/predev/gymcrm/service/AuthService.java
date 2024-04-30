package com.predev.gymcrm.service;

import com.predev.gymcrm.aop.annotation.ValidAspect;
import com.predev.gymcrm.dto.req.AccountSigninReqDto;
import com.predev.gymcrm.dto.req.OAuth2MergeReqDto;
import com.predev.gymcrm.dto.req.OAuth2SignupReqDto;
import com.predev.gymcrm.dto.resp.SearchAccountInfoRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.OAuth2;
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
        System.out.println(reqDto);
        Account account = reqDto.toEntity(passwordEncoder);

        successCount += authMapper.saveAccount(1,account);
        successCount += authMapper.saveUser(account.getAccountId());

        if(successCount < 2) {
            throw new SaveException();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void oAuth2Signup(OAuth2SignupReqDto oAuth2SignupReqDto) {
        int successCount = 0;
        Account account = oAuth2SignupReqDto.toEntity(passwordEncoder);
        successCount += authMapper.saveAccount(1, account);
        successCount += authMapper.saveUser(account.getAccountId());
        successCount += authMapper.saveOAuth2(oAuth2SignupReqDto.toOAuth2Entity(account.getAccountId()));


        if(successCount < 3) {
            throw new SaveException();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void trainerSignup(AccountSignupReqDto reqDto) {
        int successCount = 0;
        System.out.println(reqDto);
        Account account = reqDto.toEntity(passwordEncoder);

        successCount += authMapper.saveAccount(98,account);
        successCount += authMapper.saveTrainer(account.getAccountId());

        if(successCount < 2) {
            throw new SaveException();
        }
    }

    public void oAuth2Merge(OAuth2MergeReqDto oAuth2MergeReqDto) {
        Account account = authMapper.findAccountByUsername(oAuth2MergeReqDto.getUsername());

        System.out.println(oAuth2MergeReqDto);

        if(account == null) {
            throw  new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }
        if(!passwordEncoder.matches(oAuth2MergeReqDto.getPassword(), account.getPassword())){
            throw new BadCredentialsException("사용자 정보를 확인하세요");
        }
        OAuth2 oAuth2 = OAuth2.builder()
                .oauth2Name(oAuth2MergeReqDto.getOauth2Name())
                .accountId(account.getAccountId())
                .oauth2ProviderName(oAuth2MergeReqDto.getOauth2ProviderName())
                .build();
    }

    public String Signin(AccountSigninReqDto reqDto) {
        Account account = authMapper.findAccountByUsername(reqDto.getUsername());

        if(account == null ) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }
        if (!passwordEncoder.matches(reqDto.getPassword(), account.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }
        return jwtProvider.generateToken(account);
    }

    public int findUserIdByAccountId(int accountId) {
        return authMapper.findUserIdByAccountId(accountId);
    }

    public SearchAccountInfoRespDto getAccountInfoByAccountId(int accountId) {
        Account account = authMapper.findAccountByAccountId(accountId);
        System.out.println(account);
        if(account == null) {
            System.out.println("account가 null입니다");
            return null;
        }
        return SearchAccountInfoRespDto.builder()
                .accountId(account.getAccountId())
                .username(account.getUsername())
                .name(account.getName())
                .phone(account.getPhone())
                .email(account.getEmail())
                .build();
    }

}
