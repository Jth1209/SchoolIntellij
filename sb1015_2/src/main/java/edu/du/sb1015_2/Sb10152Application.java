package edu.du.sb1015_2;

import edu.du.sb1015_2.entity.MyData;
import edu.du.sb1015_2.repository.MyDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor//autowired없이 리퍼지토리를 참조할 수 있다.
public class Sb10152Application {

//    @Autowired
    final MyDataRepository myDataRepository;

//    public Sb10152Application(MyDataRepository myDataRepository) {
//        this.myDataRepository = myDataRepository;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Sb10152Application.class, args);
    }

    @PostConstruct//테스트건 뭐건 프로젝트를 실행 시키면 얘도 실행됨.
    public void init() {
        MyData d1 = new MyData();
        d1.setName("kim");
        d1.setEmail("kim@gilbut.co.kr");
        d1.setAge("23");
        d1.setMemo("this is my data!");
        myDataRepository.saveAndFlush(d1);
        MyData d2 = new MyData();
        d2.setName("lee");
        d2.setEmail("lee@flower");
        d2.setAge("15");
        d2.setMemo("my girl friend");
        myDataRepository.saveAndFlush(d2);
        MyData d3 = new MyData();
        d3.setName("choi");
        d3.setEmail("choi@happy");
        d3.setAge("37");
        d3.setMemo("my coworker~");
        myDataRepository.saveAndFlush(d3);
    }
}

