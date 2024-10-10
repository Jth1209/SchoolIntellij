package edu.du.db1010.main;

import edu.du.db1010.config.AppCtx2;
import edu.du.db1010.spring.Client2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx2.class);//Client.afterPropertiesSet() => 빈 생성

//        Client client = ctx.getBean(Client.class);
        Client2 client = ctx.getBean(Client2.class);
        client.send();//Client.send()tohost
        ctx.close();//Client.destroy() => 빈 소멸 ==> ctx.close()가 있어야 수행됨.
    }
}
