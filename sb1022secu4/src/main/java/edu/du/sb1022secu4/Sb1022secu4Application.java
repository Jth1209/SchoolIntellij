package edu.du.sb1022secu4;

import edu.du.sb1022secu4.entity.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@SpringBootApplication
public class Sb1022secu4Application {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication.run(Sb1022secu4Application.class, args);
    }

    @PostConstruct
    public void init(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Member member1 = new Member();
        member1.setEmail("user1");
        member1.setPassword("1234");
        member1.setUsername("testuser1");
        Member member2 = new Member();
        member2.setEmail("user2");
        member2.setPassword("4321");
        member2.setUsername("testuser2");
        em.persist(member1);
        em.persist(member2);
        em.getTransaction().commit();
    }
}
