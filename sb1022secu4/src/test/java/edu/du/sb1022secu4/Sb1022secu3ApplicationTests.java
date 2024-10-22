package edu.du.sb1022secu4;

import edu.du.sb1022secu4.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@SpringBootTest
class Sb1022secu3ApplicationTests {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Test
    void contextLoads() {
    }

    @Test
    void 테스트() {

    }
}
