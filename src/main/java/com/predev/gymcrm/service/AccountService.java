package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.AdminEditPasswordReqDto;
import com.predev.gymcrm.dto.req.UserEditPasswordReqDto;
import com.predev.gymcrm.dto.resp.TrainerSearchAccountInfoRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.exception.ValidException;
import com.predev.gymcrm.repository.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthMapper authMapper;


    public void editAccountPassword(UserEditPasswordReqDto reqDto) {
        Account account = authMapper.findAccountByAccountId(reqDto.getAccountId());
        String encodedPassword = account.getPassword();

        if (!passwordEncoder.matches(reqDto.getPrevPassword(), encodedPassword)) {
            throw new ValidException(Map.of("oldPassword", "비밀번호 인증에 실패하였습니다 \n다시입력하세요"));
        }
        if (passwordEncoder.matches(reqDto.getPassword(), encodedPassword)) {
            throw new ValidException(Map.of("newPassword", "새로운 비밀번호는 이전 비밀번호와 같을 수 없습니다 \n다시입력하세요"));
        }
        if (!reqDto.getPassword().equals(reqDto.getCheckPassword())) {
            throw new ValidException(Map.of("newPasswordCheck", "새로운 비밀번호가 서로 일치하지 않습니다 \n다시입력하세요"));
        }
        account.setPassword(passwordEncoder.encode(reqDto.getPassword()));
        authMapper.updateAccountPassword(account);
    }

    public int editAdminPassword(AdminEditPasswordReqDto reqDto) {
        System.out.println(reqDto);
        Account account = authMapper.findAccountByUsername("admin");
        String encodedPassword = account.getPassword();
        if (!passwordEncoder.matches(reqDto.getPrevPassword(), encodedPassword)) {
            throw new ValidException(Map.of("oldPassword", "비밀번호 인증에 실패하였습니다 \n다시입력하세요"));
        }
        if (passwordEncoder.matches(reqDto.getPassword(), encodedPassword)) {
            throw new ValidException(Map.of("newPassword", "새로운 비밀번호는 이전 비밀번호와 같을 수 없습니다 \n다시입력하세요"));
        }
        return authMapper.updateAdminPassword(reqDto.getPassword());
    }

    public TrainerSearchAccountInfoRespDto selectAccountInfoByAccountId(int accountId) {
        Account account = authMapper.findAccountByAccountId(accountId);
        System.out.println(account);
        if (account == null) {
            System.out.println("account가 null입니다");
            return null;
        }
        return TrainerSearchAccountInfoRespDto.builder()
                .accountId(account.getAccountId())
                .username(account.getUsername())
                .name(account.getName())
                .phone(account.getPhone())
                .email(account.getEmail())
                .build();
    }
}
