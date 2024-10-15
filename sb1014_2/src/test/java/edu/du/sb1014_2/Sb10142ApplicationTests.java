package edu.du.sb1014_2;

import edu.du.sb1014_2.entity.Board;
import edu.du.sb1014_2.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class Sb10142ApplicationTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void 리스트테스트(){
        List<Board> boards = boardRepository.selectBoardList();
        for(Board board : boards){
            log.info(board);
        }
        log.info("종료");
    }


}
