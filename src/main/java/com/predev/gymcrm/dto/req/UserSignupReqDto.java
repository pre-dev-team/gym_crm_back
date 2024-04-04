package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.User;
import com.predev.gymcrm.repository.UserMapper;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class UserSignupReqDto {

    @Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "영문자, 숫자 5 ~ 10자리 형식이어야 합니다")
    private String userUsername;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$", message = "하나의 영문자, 숫자, 특수문자를 포함한 5 ~ 128자리 형식이어야 합니다")
    private String userPassword;    // 1q2w3e4r!
    @Pattern(regexp = "^[가-힇]{1,}$", message = "한글문자 형식이어야 합니다")
    private String userName;
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$\n", message = "휴대전화 형식이어야 합니다.(ex: 01x - xxxx - xxxx)")
    private String userPhone;
    @Email(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{1,3}$", message = "이메일 형식이어야 합니다")
    private String userEmail;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .userUsername(userUsername)
                .userPassword(passwordEncoder.encode(userPassword))
                .userName(userName)
                .userPhone(userPhone)
                .userEmail(userEmail)
                .build();
    }
}
