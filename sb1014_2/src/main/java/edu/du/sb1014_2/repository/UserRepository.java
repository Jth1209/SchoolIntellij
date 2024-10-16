package edu.du.sb1014_2.repository;

import edu.du.sb1014_2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select new User(u.uid , u.id , u.password, u.email, u.name) from User u where u.id = :i and u.password = :p")
    User findUser(@Param("i")String id , @Param("p") String password);

    @Query("select count(*) from User u where u.id = :i and u.password = :p ")
    int findUserCount(@Param("i")String id , @Param("p")String password);
}
