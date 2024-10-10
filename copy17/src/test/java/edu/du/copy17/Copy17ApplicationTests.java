package edu.du.copy17;

import edu.du.copy17.dao.ArticleDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Copy17ApplicationTests {

    @Autowired
    ArticleDao dao;

    @Test
    void 게시판건수_출력(){
        System.out.println(dao.selectCount());
    }

}
