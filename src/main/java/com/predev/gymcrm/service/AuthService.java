package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.*;
import com.predev.gymcrm.entity.Trainer;
import com.predev.gymcrm.entity.User;
import com.predev.gymcrm.exception.SaveException;
import com.predev.gymcrm.jwt.JwtProvider;
import com.predev.gymcrm.repository.TrainerMapper;
import com.predev.gymcrm.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private TrainerMapper trainerMapper;

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

    @Transactional(rollbackFor = Exception.class)
    public void trainerSignup(TrainerSignupReqDto trainerSignupReqDto) {
        int successCount = 0;
        Trainer trainer = trainerSignupReqDto.toEntity(passwordEncoder);

        successCount += trainerMapper.saveTrainer(trainer);
        // trainer role관련 코드가 필요합니다.

        if(successCount < 1) {  //  role추가 후 2로 수정해야합니다.
            throw new SaveException();
        }

        trainerMapper.saveTrainer(trainerSignupReqDto.toEntity(passwordEncoder));
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

    public String trainerSignin(TrainerSigninReqDto trainerSigninReqDto) {
        Trainer trainer = trainerMapper.findTrainerByTrainerUsername(trainerSigninReqDto.getTrainerUsername());
        System.out.println(trainer);
        if(trainer == null ) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }
        if(!passwordEncoder.matches(trainerSigninReqDto.getTrainerPassword(), trainer.getTrainerPassword())) {
                throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }

//        return jwtProvider.generateJwt(trainer); jwt에 trainer관련이 없어 주석처리해놨습니다.
    }





}
