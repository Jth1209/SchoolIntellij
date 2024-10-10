package edu.du.copy17.dao;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class IdGenerator{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int generateNextId(@Nullable String sequenceName) {
        int id = jdbcTemplate.queryForObject("select next_value from id_sequence where sequence_name = ? for update", Integer.class, sequenceName);

        id++;

        jdbcTemplate.update("update id_sequence set next_value=? where sequence_name = ?", id, sequenceName);

        return id;
    }
}
