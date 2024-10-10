package edu.du.db1010.config;

import edu.du.db1010.spring.Client;
import edu.du.db1010.spring.Client2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppCtx2 {

    @Bean
    @Scope("singleton")//@Scope의 값 중, prototype과 singleton의 차이를 이해(별도로 생성 , 하나만 생성해서 호출)/ 대부분 singleton으로 하나만 잡아서 함./default로 singleton임
    public Client client(){//implement한 class 내의 메소드가 가장 먼저 실행되고 close 시에도 맨 마지막에 실행된다.
        Client client= new Client();
        client.setHost("host");
        return client;
    }

    @Bean(destroyMethod = "close")//초기화 때 실행되는 메소드와 소멸할 때 실행되는 메소드를 설정. 직접 설정한 것은 실행 직후, close 직후 실행된다.
    public Client2 client2(){
        Client2 client= new Client2();
        client.setHost("host");
        client.connect();
        return client;
    }
}
