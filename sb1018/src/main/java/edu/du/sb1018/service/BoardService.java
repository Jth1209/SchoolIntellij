package edu.du.sb1018.service;

import edu.du.sb1018.entity.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardService {
    void updateBoard(int id , String title,String content);
    void deleteBoard(int id);
    void hitCount(int id);
    void insertBoard(String content , String title);
    List<Board> selectAllBoard();
    Board selectById(int id);
}
