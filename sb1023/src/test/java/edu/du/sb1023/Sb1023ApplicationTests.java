package edu.du.sb1023;

import edu.du.sb1023.entity.Member;
import edu.du.sb1023.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
class Sb1023ApplicationTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void contextLoads() {
    }

    @Test
    void 테스트(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Member member1 = new Member();
        Team team = new Team();
        member1.setId("member1");
        member1.setUsername("손흥민");
        team.setId("team1");
        team.setName("tot");
        em.persist(team);//팀이 생겨야

        member1.setTeam(team);
        em.persist(member1);//얘도 넣지

        //아래거가 훨씬 간편하고 좋네
        Team team2 = new Team("team2","뮌헨");
        em.persist(team2);
        Member member2 = new Member("member2","김민재",team2);
        em.persist(member2);

        em.getTransaction().commit();
    }

    @Test
    void 조인테스트(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String q = "select m from Member m join m.team t where t.name = :teamName";
        TypedQuery<Member> query = em.createQuery(q, Member.class);
        query.setParameter("teamName","tot");
        List<Member> members = query.getResultList();
        for(Member member : members) {
            System.out.println(member);
        }

        em.getTransaction().commit();
    }

    @Test
    void 조인테스트2(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Member member = em.find(Member.class,"member1");
        Team team = member.getTeam();
        System.out.println(team);
        System.out.println(member);

        em.getTransaction().commit();
    }

    @Test
    void 업데이트테스트2(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Team team = new Team("team3", "바르셀로나");
        em.persist(team);
        Member member = em.find(Member.class,"member1");
        member.setTeam(team);

        em.getTransaction().commit();
    }

    @Test
    void 연관관계제거(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Member member = em.find(Member.class,"member1");
        member.setTeam(null);

        em.getTransaction().commit();
    }

    @Test
    void 연관된_엔티티제거(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

//        Team team = em.find(Team.class,"team3");
//        em.remove(team);

//        em.remove(em.find(Team.class,"team3"));
        em.remove(em.find(Team.class,"team1"));
        em.getTransaction().commit();
    }

    @Test
    void 연관테이터(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Team team = em.find(Team.class,"team1");
//        team.getMembers().forEach(t-> System.out.println(t.getUsername()));
        List<Member> members = team.getMembers();
        for(Member member : members) {
            System.out.println(member);
        }

        em.getTransaction().commit();
    }
}
