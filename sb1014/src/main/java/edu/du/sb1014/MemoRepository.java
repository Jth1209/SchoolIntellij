package edu.du.sb1014;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {//DAO와 같은 역할
    List<Memo> findByIdBetween(Long min, Long max);//query method
    List<Memo> findByIdBetweenOrderByIdDesc(Long min, Long max);

    @Query("select m from Memo m order by m.id desc")//@Entity로 설정한 class의 명으로 table명을 설정함.
    List<Memo> getListDesc();

    @Query("select count(m) from Memo m ")
    int getCount();

    @Query("select m from Memo m where m.id = :id")
    Memo getByid(Long id);
}
