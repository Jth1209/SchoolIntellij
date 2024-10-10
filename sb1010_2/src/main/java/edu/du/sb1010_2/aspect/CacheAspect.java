package edu.du.sb1010_2.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Order(2)
public class CacheAspect {
    private Map<Long,Object> cache = new HashMap<Long,Object>();
    @Pointcut("execution(public * *..chap07..*(long))")
    public void cacheTarget(){

    }

    @Around("cacheTarget()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Long num = (Long) joinPoint.getArgs()[0]; //hashmap의 key값을 꺼내옴.(Long)
        if(cache.containsKey(num)){//이미 hashmap에 해당 key가 존재한다면, 키를 꺼내온다.
            System.out.printf("CacheAspect: find ad Cache[%d]\n",num);
            return cache.get(num);
        }
        Object result = joinPoint.proceed();//ExeTimeAspect로 이동 -> 이후 실제 실행 클래스인 RecCalculator를 실행하고, 해당 실행 시간을 반환하고 현재 클래스의 아래 코드를 실행.
        cache.put(num,result);//key값이 존재하지 않을 때, key를 hashmap에 추가
        System.out.printf("CacheAspect: append at Cache[%d]\n",num);
        return result;
    }
}
