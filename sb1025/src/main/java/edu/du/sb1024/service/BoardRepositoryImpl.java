package edu.du.sb1024.service;

import edu.du.sb1024.fileuploadboard.entity.Board;
import edu.du.sb1024.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class BoardRepositoryImpl {

    final EntityManagerFactory emf;

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public void insertBoard(String title, String content) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Board board = Board.builder()
                .title(title)
                .contents(content).createdDatetime(LocalDateTime.now().toString().substring(0, 10)).creatorId("admin").deletedYn("N").hitCnt(0).build();

        em.persist(board);
        em.getTransaction().commit();
    }

    public void updateBoard(Integer id, String title, String content) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Board board = em.find(Board.class, id);
        board.setTitle(title);
        board.setContents(content);
        board.setUpdatedDatetime(LocalDateTime.now().toString().substring(0, 10));
        board.setUpdaterId("admin");
        board.setHitCnt(0);
        em.persist(board);

        em.getTransaction().commit();
    }
    public void deleteBoard(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Board board = em.find(Board.class, id);
        board.setDeletedYn("Y");
        em.persist(board);
        em.getTransaction().commit();
    }

    public void updateHit(int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Board board = em.find(Board.class, id);
        board.setHitCnt(board.getHitCnt()+1);
        em.persist(board);
        em.getTransaction().commit();
    }
}
