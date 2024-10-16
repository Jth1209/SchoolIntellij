package edu.du.sb1015_2;

import edu.du.sb1015_2.entity.MyData;
import edu.du.sb1015_2.repository.MyDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Log4j2
@Transactional
class Sb10152ApplicationTests {
    @Autowired
    MyDataRepository myDataRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void 데이터_가져오기(){
        List<MyData> datas = myDataRepository.findAll();
        for(MyData myData : datas){
            log.info(myData);
        }
    }

    @Test
    void 업데이트_테스트(){
        myDataRepository.updateData(2L,"Lee","Lee@gmail.com","25","my exGirlFriend");
        log.info(myDataRepository.findById(2L).get());
    }

    @Test
    void 딜리트_테스트(){
        myDataRepository.deleteById(1L);
        List<MyData> datas = myDataRepository.findAll();
        for(MyData myData : datas){
            log.info(myData);
        }
    }

    @Test
    void 인서트_테스트(){
        MyData myData = new MyData("Ann","Ann@gmail.com","25","he's my BF!");
        myDataRepository.saveAndFlush(myData);
        List<MyData> datas = myDataRepository.findAll();
        for(MyData m : datas){
            log.info(m);
        }
    }
}
