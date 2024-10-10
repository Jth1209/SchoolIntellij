package edu.du.sb1010_2.config;

import edu.du.sb1010_2.aspect.CacheAspect;
import edu.du.sb1010_2.aspect.ExeTimeAspect;
import edu.du.sb1010_2.chap07.Calculator;
import edu.du.sb1010_2.chap07.RecCalculator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtxWithCache {

    @Bean
    public CacheAspect cacheAspect() {
        return new CacheAspect();
    }

    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    @Bean
    Calculator calculator() {
        return new RecCalculator();
    }
}
