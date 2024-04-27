package com.predev.gymcrm.aop;

import com.predev.gymcrm.dto.req.AccountSignupReqDto;
import com.predev.gymcrm.dto.req.OAuth2SignupReqDto;
import com.predev.gymcrm.exception.ValidException;
import com.predev.gymcrm.repository.AuthMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ValidAop {

    @Autowired
    private AuthMapper authMapper;

    @Pointcut("@annotation(com.predev.gymcrm.aop.annotation.ValidAspect)")
    private void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();

        Object[] args = proceedingJoinPoint.getArgs();

        BeanPropertyBindingResult bindingResult = null;

        for(Object arg : args) {
            if(arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
            }
        }

        if(methodName.equals("userSignup") || methodName.equals("trainerSignup")) {
            AccountSignupReqDto reqDto = null;

            for (Object arg : args) {
                if (arg.getClass() == AccountSignupReqDto.class) {
                    reqDto = (AccountSignupReqDto) arg;
                }
            }

            if(authMapper.findAccountByUsername(reqDto.getUsername()) != null){
                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 사용자이름입니다.");
                bindingResult.addError(objectError);
            }

            if(authMapper.findAccountByPhone(reqDto.getPhone()) != null) {
                ObjectError objectError = new FieldError("phone", "phone", "이미 사용중인 전화번호입니다.");
                bindingResult.addError(objectError);
            }

            if(bindingResult.hasErrors()) {
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                Map<String, String> errorMap = new HashMap<>();
                for(FieldError fieldError : fieldErrors) {
                    String fieldName = fieldError.getField();   // DTO 변수명
                    String message = fieldError.getDefaultMessage();    // 메세지내용
                    errorMap.put(fieldName, message);
                }
                throw new ValidException(errorMap);
            }
        }
        if(methodName.equals("oAuth2Signup")) {
            OAuth2SignupReqDto oAuth2SignupReqDto = null;
            for (Object arg : args) {
                if (arg.getClass() == OAuth2SignupReqDto.class) {
                    oAuth2SignupReqDto = (OAuth2SignupReqDto) arg;
                }
            }
        }

        return proceedingJoinPoint.proceed();
    }
}
