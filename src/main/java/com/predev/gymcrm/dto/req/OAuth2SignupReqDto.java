package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.OAuth2;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class OAuth2SignupReqDto {

    @Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "영문자, 숫자 5 ~ 10자리 형식이어야 합니다")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$", message = "하나의 영문자, 숫자, 특수문자를 포함한 5 ~ 128자리 형식이어야 합니다")
    private String password;    // 1q2w3e4r!
    @Pattern(regexp = "^[가-힇]{1,}$", message = "한글문자 형식이어야 합니다")
    private String name;
    @Email(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{1,3}$", message = "이메일 형식이어야 합니다")
    private String email;
    @Pattern(regexp = "^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$", message = "휴대전화 형식이어야 합니다.(ex: 01x - xxxx - xxxx)")
    private String phone;

    @NotBlank
    private String oauth2Name;
    @NotBlank
    private String oauth2ProviderName;

    public Account toEntity(BCryptPasswordEncoder passwordEncoder) {
        return Account.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .phone(phone)
                .build();
    }

    public OAuth2 toOAuth2Entity(int accountId) {
        return OAuth2.builder()
                .oauth2Name(oauth2Name)
                .accountId(accountId)
                .oauth2ProviderName(oauth2ProviderName)
                .build();
    }
}