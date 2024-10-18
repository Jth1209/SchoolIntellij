package edu.du.sb1018;

import edu.du.sb1018.entity.Dept;
import edu.du.sb1018.entity.Emp;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import java.util.List;

@SpringBootTest
@Log4j2
class Sb1018ApplicationTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Test
    void contextLoads() {
    }

    @Test
    void 영속성_테스트(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); //transaction은 프로세스 중간에 예외 발생 시 , rollback 해주는 역할.
        tx.begin();

        Dept dept = em.find(Dept.class,10); //find는 select처럼 역할을 수행한다.
        dept.setDname("서울");
        System.out.println(dept);
        tx.commit();
    }

    @Test
    void 영속성_merge2(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); //transaction은 프로세스 중간에 예외 발생 시 , rollback 해주는 역할.
        tx.begin();

        Dept dept = em.find(Dept.class,10);//find는 select처럼 역할을 수행한다. (영속성 부여 deptno = 10)
        dept.setDname("ACCOUNT");
        dept.setLoc("성남");
        System.out.println(dept);//1차 저장.
        em.detach(dept);//detach 연결되어있던 db정보와 잠깐 연결을 끊음. (영속성을 잃음.)
        em.merge(dept);//잃어버린 영속성을 다시 부여.
        tx.commit();//이게 실행되어야 db가 변경됨.
    }
    
    @Test
    void 영속성_persist(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Dept dept = em.find(Dept.class,10);
        System.out.println(dept);
        Dept dept2 = new Dept();
        dept2.setDeptno(50);
        dept2.setLoc("서울");
        dept2.setDname("연구소");
        em.persist(dept2);//db에 해당 데이터가 없으면 insert 역할을 수행.
        tx.commit();
    }

    @Test
    void 영속성_remove(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Dept dept = em.find(Dept.class,50);
        System.out.println(dept);
        em.remove(dept);
        tx.commit();
    }

    @Test
    void 영속성_update(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Dept dept = em.find(Dept.class,10);
        dept.setDname("ACCOUNT");
        dept.setLoc("SEOUL");
        tx.commit();
    }

    @Test
    void 쿼리_테스트(){
        Dept dept = em.find(Dept.class,10);
        System.out.println(dept);

        TypedQuery<Dept> query = em.createQuery("select d from Dept d", Dept.class); //query에 해당하는 정보를 db에서 가져와서 TypedQuery에 Dept형으로 담아둠
        List<Dept> depts = query.getResultList();//getResultList()를 이용해서 담아놓은 결과를 List<Dept> 변수에 저장함.
        for(Dept d : depts){
            System.out.println(d);
        }
    }

    @Test
    void 영업사원_불러오기(){
        TypedQuery<Emp> query = em.createQuery("select e from Emp e",Emp.class);
        List<Emp> emps = query.getResultList();
        for(Emp e : emps){
            System.out.println(e);
        }
    }

    @Test
    void 쿼리_테스트2(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        TypedQuery<Dept> query = em.createQuery("select d from Dept d", Dept.class); //query에 해당하는 정보를 db에서 가져와서 TypedQuery에 Dept형으로 담아둠
        List<Dept> depts = query.getResultList();//getResultList()를 이용해서 담아놓은 결과를 List<Dept> 변수에 저장함.
        for(Dept d : depts){
            System.out.println(d);
        }
        depts.get(0).setLoc("서울");
        tx.commit();
    }

    @Test
    void 어려운_쿼리_테스트(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        TypedQuery<Dept> query = em.createQuery("select d from Dept d where d.dname = :dname", Dept.class); //query에 해당하는 정보를 db에서 가져와서 TypedQuery에 Dept형으로 담아둠
        query.setParameter("dname","ACCOUNT");
        List<Dept> depts = query.getResultList();//getResultList()를 이용해서 담아놓은 결과를 List<Dept> 변수에 저장함.
        for(Dept d : depts){
            System.out.println(d);
        }

        TypedQuery<Emp> query2 = em.createQuery("select e from Emp e where e.deptno = :dno",Emp.class);
        query2.setParameter("dno",depts.get(0).getDeptno());
        List<Emp> emps = query2.getResultList();
        for(Emp e : emps){
            System.out.println(e);
        }
    }
}
