package edu.du.sb1010_2.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

@Aspect
@Order(1)
public class ExeTimeAspect {

    private void publicTarget(){
    }

    @Around("execution(public * *..chap07..*(..))")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try{
            Object result = joinPoint.proceed();
            return result;
        }finally{
            long finish = System.nanoTime();
            Signature signature = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) runnigTime : %d ns\n",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    signature.getName(), Arrays.toString(joinPoint.getArgs()),
                    (finish - start));
        }
    }
}
