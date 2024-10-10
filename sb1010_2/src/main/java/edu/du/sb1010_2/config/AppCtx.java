package edu.du.sb1010_2.config;

import edu.du.sb1010_2.aop2.Greet;
import edu.du.sb1010_2.aop2.MorningGreet;
import edu.du.sb1010_2.aspect.ExeTimeAspect;
import edu.du.sb1010_2.chap07.Calculator;
import edu.du.sb1010_2.chap07.ImpeCalculator;
import edu.du.sb1010_2.chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)//이건 알아두세용!
public class AppCtx {
    @Bean
    public ExeTimeAspect exeTimeAspect() {//AOP 선언 (빈 등록)
        return new ExeTimeAspect();
    }
//    @Bean
//    public Calculator calculator() {
//        return new RecCalculator();
//    }
    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }

    @Bean
    Greet greet(){
        return new MorningGreet();
    }
}
