package edu.du.sb1021_2;

import edu.du.sb1021_2.dao.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.Date;

@SpringBootApplication
public class Sb10212Application {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication.run(Sb10212Application.class, args);
    }

    @PostConstruct
    public void init(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Member m1 = new Member();
        m1.setEmail("user1");
        m1.setName("user1");
        m1.setPassword("1234");
        m1.setPasswordCheck("1234");
        m1.setRegisterDateTime(new Date());
        m1.setRole("USER");
        em.persist(m1);
        em.getTransaction().commit();
    }
}
