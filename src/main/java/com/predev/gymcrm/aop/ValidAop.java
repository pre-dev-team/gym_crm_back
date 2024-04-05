package com.predev.gymcrm.aop;

import com.predev.gymcrm.dto.req.AccountSignupReqDto;
import com.predev.gymcrm.exception.ValidException;
import com.predev.gymcrm.repository.UserMapper;
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
    private UserMapper userMapper;

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

        if(methodName.equals("userSignup")) {
            AccountSignupReqDto accountSignupReqDto = null;

            for (Object arg : args) {
                if (arg.getClass() == AccountSignupReqDto.class) {
                    accountSignupReqDto = (AccountSignupReqDto) arg;
                }
            }

            if(userMapper.findUserByUsername(accountSignupReqDto.getUserUsername()) != null){
                ObjectError objectError = new FieldError("userUsername", "userUsername", "이미 존재하는 사용자이름입니다.");
                bindingResult.addError(objectError);
            }

            if(userMapper.findUserByPhone(accountSignupReqDto.getUserPhone()) != null) {
                ObjectError objectError = new FieldError("userPhone", "userPhone", "이미 사용중인 전화번호입니다.");
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

        return proceedingJoinPoint.proceed();
    }
}
