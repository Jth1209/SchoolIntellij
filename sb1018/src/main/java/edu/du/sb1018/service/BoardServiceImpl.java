package edu.du.sb1018.service;

import edu.du.sb1018.entity.Board;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    public void updateBoard(int id , String title, String content) {//게시글 정보 변경
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Board b = em.find(Board.class,id);
        b.setTitle(title);
        b.setContents(content);
        b.setUpdater_id("admin");
        b.setUpdated_datetime(new Date());
        b.setHit_cnt(0);

        em.getTransaction().commit();
    }

    public void hitCount(int id){//조회수
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Board b = em.find(Board.class,id);
        b.setHit_cnt(b.getHit_cnt()+1);
        em.getTransaction().commit();
    }

    public void deleteBoard(int id) {//게시글 삭제
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Board board = em.find(Board.class, id);
        em.remove(board);
        em.getTransaction().commit();
    }


    public void insertBoard(String content, String title) {//게시글 추가 (아마 auto_increment에서 문제 생길거임)
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Board b = new Board();

        b.setContents(content);
        b.setTitle(title);
        b.setCreated_datetime(new Date());
        b.setCreator_id("admin");
        b.setDeleted_yn("N");
        b.setHit_cnt(0);

        em.persist(b);
        em.getTransaction().commit();
    }

    public List<Board> selectAllBoard() {//모든 게시물 불러오기
        String q = "select b from Board b where b.deleted_yn = :delete order by b.board_idx desc";
        TypedQuery<Board> query = em.createQuery(q,Board.class);
        query.setParameter("delete","N");
        List<Board> boards = query.getResultList();
        return boards.isEmpty()?null:boards;
    }

    public Board selectById(int id) {//게시글 상세 정보 불러오기
        String q = "select b from Board b where b.board_idx = :id and b.deleted_yn = :delete";
        TypedQuery<Board> query = em.createQuery(q,Board.class);
        query.setParameter("id",id);
        query.setParameter("delete","N");
        List<Board> board = query.getResultList();
        return board.isEmpty()?null:board.get(0);
    }
}
