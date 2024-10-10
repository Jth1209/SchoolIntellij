package edu.du.sb1008.dao;

import edu.du.sb1008.dto.SimpleBbsDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ISimpleBbsDao {

    @Select("select * from simple_bbs order by id desc")//annotation을 사용해서 query를 직접 작성할 수 있다.
    public List<SimpleBbsDto> listDao();

    @Select("select * from simple_bbs where id = #{id}")
    public SimpleBbsDto viewDao(@Param("id") String id);//parameterType = "String"과 같은 코드

    @Insert("insert into simple_bbs (writer,title,content) values (#{writer},#{title},#{content})")
    public int writeDao(@Param("writer") String writer,@Param("title") String title, @Param("content") String content);//parameterType = "String"과 같은 코드

    @Delete("delete from simple_bbs where id = #{id}")
    public int deleteDao(@Param("id") String id);//parameterType = "String"과 같은 코드

    @Update("update simple_bbs set writer=#{writer},title=#{title},content=#{content} where id = #{id}")
    public int updateDao(@Param("id") String id, @Param("writer") String writer,@Param("title") String title, @Param("content") String content);//parameterType = "String"과 같은 코드
}
