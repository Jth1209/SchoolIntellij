package edu.du.sb1021_2.service;

import edu.du.sb1021_2.dao.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository {
    Member selectByEmail(String email);
    void insert(Member member);
    void update (Member member);
    List<Member> selectAll();
    int userCount();
}
