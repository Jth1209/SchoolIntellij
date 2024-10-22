package edu.du.sb1022secu4.repository;

import edu.du.sb1022secu4.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    Member selectByEmail(String email);
}
