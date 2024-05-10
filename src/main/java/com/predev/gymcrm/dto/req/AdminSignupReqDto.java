package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Account;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class AdminSignupReqDto {
    private String phone;
    private String email;
    private String secret;

    public Account toAccountEntity(BCryptPasswordEncoder passwordEncoder) {
        return Account.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin1234"))
                .name("관리자")
                .phone(phone)
                .email(email)
                .build();
    }
}
