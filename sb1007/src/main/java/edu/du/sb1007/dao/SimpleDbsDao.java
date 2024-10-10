package edu.du.sb1007.dao;

import edu.du.sb1007.dto.SimpleBbsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SimpleDbsDao implements ISimpleBbsDao{

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<SimpleBbsDto> listDao() {
        System.out.println("listDao()");

        String query = "select * from simple_bbs order by id desc";
        List<SimpleBbsDto> dtos = jdbcTemplate.query(
                query, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
        return dtos;
    }

    @Override
    public SimpleBbsDto viewDao(String id) {
        String query = "select * from simple_bbs where id = " + id;
        SimpleBbsDto dto = jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
        return dto;
    }

    @Override
    public int writeDao(String writer, String title, String content) {
        String query = "insert into simple_bbs(writer, title, content) values(?,?,?)";
        return jdbcTemplate.update(query,writer,title,content);
    }

    @Override
    public int deleteDao(String id) {
        String query = "delete from simple_bbs where id = ?";
        return jdbcTemplate.update(query,id);
    }

    @Override
    public int updateDao(String id, String writer, String title, String content) {
        String query = "update simple_bbs set writer = ?, title = ?, content = ? where id = ?";
        return jdbcTemplate.update(query,writer,title,content,id);
    }
}
