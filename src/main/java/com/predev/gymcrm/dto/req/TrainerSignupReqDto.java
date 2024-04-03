package com.predev.gymcrm.dto.req;

import com.predev.gymcrm.entity.Trainer;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class TrainerSignupReqDto {
    @Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "영문자, 숫자 5 ~ 10자리 형식이어야 합니다")
    private String trainerUsername;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$", message = "하나의 영문자, 숫자, 특수문자를 포함한 5 ~ 128자리 형식이어야 합니다")
    private String trainerPassword;    // 1q2w3e4r!
    @Pattern(regexp = "^[가-힇]{1,}$", message = "한글문자 형식이어야 합니다")
    private String trainerName;
    @Email(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{1,3}$", message = "이메일 형식이어야 합니다")
    private String trainerEmail;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message="휴대전화양식을 지켜주세요.(-포함15자이상)")
    private String trainerPhone;

    private String trainerProfileImgUrl;

    public Trainer toEntity() {
        return Trainer.builder()
                .trainerUserName(trainerUsername)
                .trainerPassword(trainerPassword)
                .trainerName(trainerName)
                .trainerEmail(trainerEmail)
                .trainerPhone(trainerPhone)
                .trainerProfileImgUrl(trainerProfileImgUrl)
                .build();
    }

}
