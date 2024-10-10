package edu.du.sb1010_2;

import edu.du.sb1010_2.aop2.Greet;
import edu.du.sb1010_2.config.AppCtx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class Sb10102Application {

    @Autowired
    Greet greet;

    private void execute(){
        greet.greeting();
    }

    public static void main(String[] args) {
        SpringApplication.run(Sb10102Application.class, args).getBean(Sb10102Application.class).execute();
    }

}
