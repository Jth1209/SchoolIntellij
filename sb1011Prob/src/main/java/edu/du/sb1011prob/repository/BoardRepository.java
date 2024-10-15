package edu.du.sb1011prob.repository;

import edu.du.sb1011prob.dto.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query("SELECT new Board(b.boardIdx,b.title,b.contents,b.hitCnt,b.creatorId,b.createdDatetime,b.updaterId,b.updatedDatetime,b.deletedYn) FROM Board b WHERE b.deletedYn = 'N' ORDER BY b.boardIdx DESC")
    List<Board> selectList();
}
