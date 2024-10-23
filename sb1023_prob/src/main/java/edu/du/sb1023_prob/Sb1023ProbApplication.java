package edu.du.sb1023_prob;

import edu.du.sb1023_prob.entity.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Date;

@SpringBootApplication
public class Sb1023ProbApplication {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication.run(Sb1023ProbApplication.class, args);
    }

    @PostConstruct
    public void init(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Member m1 = new Member();
        m1.setEmail("user1");
        m1.setName("user1");
        m1.setPassword(passwordEncoder().encode("1234"));
        m1.setPasswordCheck(passwordEncoder().encode("1234"));
        m1.setRegisterDateTime(new Date());
        m1.setRole("USER");
        em.persist(m1);
        em.getTransaction().commit();
    }
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
