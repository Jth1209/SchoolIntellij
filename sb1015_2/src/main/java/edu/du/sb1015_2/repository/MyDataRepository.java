package edu.du.sb1015_2.repository;

import edu.du.sb1015_2.entity.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {
}
