package edu.du.sb1018.service;

import edu.du.sb1018.entity.User;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findUser(String id, String password) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String q = "select u from User u where u.id = :id and u.password = :password and u.delete_yn='N' ";
        TypedQuery<User> query = em.createQuery(q , User.class);
        query.setParameter("id",id);
        query.setParameter("password",password);
        List<User> users = query.getResultList();
        em.getTransaction().commit();

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void updateUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User u = em.find(User.class, user.getUid());
        u.setId(user.getId());
        u.setPassword(user.getPassword());
        u.setName(user.getName());
        u.setEmail(user.getEmail());

        em.getTransaction().commit();
    }

    @Override
    public void deleteUser(int uid) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User u = em.find(User.class, uid);
        u.setDelete_yn("Y");

        em.getTransaction().commit();
    }

    @Override
    public void saveUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User u = new User();
        u.setId(user.getId());
        u.setPassword(user.getPassword());
        u.setName(user.getName());
        u.setEmail(user.getEmail());

        em.persist(u);
        em.getTransaction().commit();
    }
}
