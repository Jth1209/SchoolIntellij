package edu.du.sb1022secu4.service;

import edu.du.sb1022secu4.entity.Member;
import edu.du.sb1022secu4.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

@Service
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public Member selectByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String q = "select m from Member m where m.email = :email";
        TypedQuery<Member> query = em.createQuery(q, Member.class);
        query.setParameter("email", email);
        Member member = query.getSingleResult();

        em.getTransaction().commit();
        return member;
    }
}
