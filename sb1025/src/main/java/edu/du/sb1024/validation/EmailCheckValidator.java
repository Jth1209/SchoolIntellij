package edu.du.sb1024.validation;

import edu.du.sb1024.spring.MemberRegisterService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailCheckValidator implements ConstraintValidator<EmailCheck, String>{

    @Autowired
    MemberRegisterService memberRegisterService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean isValid = false;
        if (email == null || email.isEmpty()) {
            return true; // Null 또는 빈 문자열인 경우는 다른 검증에서 처리
        }
        if(memberRegisterService.checkEmail(email) != 1){
            isValid = true;
        }
        return isValid; // 이메일이 존재하면 false 반환
    }
}
