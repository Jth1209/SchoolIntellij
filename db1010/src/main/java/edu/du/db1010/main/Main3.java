package edu.du.db1010.main;

import edu.du.db1010.config.AppCtx2;
import edu.du.db1010.spring.Client;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main3 {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx2.class);//Client.afterPropertiesSet() => 빈 생성

        Client client1 = ctx.getBean(Client.class);
        Client client2 = ctx.getBean(Client.class);
        System.out.println("client1 == client2 " + (client1 == client2));

        ctx.close();
    }
}
