package edu.du.sb1014_2.repository;

import edu.du.sb1014_2.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query("SELECT new Board(b.boardIdx, b.title, b.contents, b.hitCnt, b.creatorId, b.createdDatetime " +
            ", b.updaterId, b.updatedDatetime, b.deletedYn) FROM Board b WHERE b.deletedYn = 'N' ORDER BY b.boardIdx DESC")
    List<Board> selectBoardList();

    @Query("SELECT new Board(b.boardIdx , b.title, b.contents , b.hitCnt , b.creatorId, b.createdDatetime, b.updaterId,b.updatedDatetime,b.deletedYn) From Board b where b.boardIdx = :boardIdx and b.deletedYn = 'n'")
    Board selectBoardById(int boardIdx);

    @Modifying
    @Transactional
    @Query("delete from Board b where b.boardIdx = :boardIdx")
    void deleteBoardById(int boardIdx);

    @Modifying
    @Transactional
    @Query("update Board b set b.title = :title , b.contents = :content , b.updatedDatetime = :date , b.updaterId = 'admin' where b.boardIdx = :boardIdx")
    void updateBoard(String title, String content, Date date, int boardIdx);
}
