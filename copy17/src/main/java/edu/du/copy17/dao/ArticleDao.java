package edu.du.copy17.dao;


import edu.du.copy17.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    @Select("select count(*) from article")
    public int selectCount();

    @Select("select article_id id, group_id groupId, sequence_no sequenceNumber, posting_date postingDate, read_count readCount, writer_name writerName, password, title from article order by sequence_no desc limit #{firstRow}, #{endRow}")
    public List<Article> select(@Param("firstRow") int firstRow, @Param("endRow") int endRow);

//	@Insert("INSERT INTO user(email, name, password) VALUES (#{user.email}, #{user.name}, #{user.password})")
//	@Options(useGeneratedKeys = true, keyProperty = "dbid", keyColumn="dbid") //SQL이 생성한 KEY 값을 매핑된 객체의 dbid 필드에도 담아주겠다.
//	int insert(@Param("user") User user);

    @Insert("insert into article "
            + "(group_id, sequence_no, posting_date, read_count, "
            + "writer_name, password, title, content) "
            + "values (#{article.groupId}, #{article.sequenceNumber}, #{article.postingDate}, 0, #{article.writerName}, #{article.password}, #{article.title}, #{article.content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="article_id") //SQL이 생성한 KEY 값을 매핑된 객체의 id 필드에도 담아주겠다.
    public int insert(@Param("article") Article article);

    @Select("select article_id id, group_id groupId, sequence_no sequenceNumber, posting_date postingDate, read_count readCount, writer_name writerName, password, title, content from article where article_id = #{articleId}")
    public Article selectById(@Param("articleId") int articleId);

    @Update("update article "
            + "set read_count = read_count + 1 "
            + "where article_id = #{articleId}")
    public void increaseReadCount(@Param("articleId")int articleId);
    @Select("select min(sequence_no) from article where sequence_no < #{searchMaxSeqNum} and sequence_no >=#{searchMinSeqNum}")
    public String selectLastSequenceNumber(@Param("searchMaxSeqNum") String searchMaxSeqNum, @Param("searchMinSeqNum") String searchMinSeqNum);
    @Update("update article set title = #{article.title} , content = #{article.content} where article_id = #{article.id}")
    public int update(@Param("article") Article article);
    @Delete("delete from article where article_id = #{articleId}")
    public void delete(@Param("articleId") int articleId);

}
