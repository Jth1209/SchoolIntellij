package edu.du.sb1010_2.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class SampleAspect {

    @Around("execution(* *..aop2.*Greet.*(..))")
    public Object aroudAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("===========Around Advice =============");
        System.out.println("처리전");

        Object result = pjp.proceed();
        System.out.println("처리후");

        return result;
    }

    @After("execution(* *..aop2.*Greet.*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("==========After Advice===========");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(String.format("메서드:%s", joinPoint.getSignature().getName()));
    }

    @Before("execution(* *..aop2.*Greet.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("==========Before Advice===========");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(String.format("메서드:%s", joinPoint.getSignature().getName()));
    }
}
