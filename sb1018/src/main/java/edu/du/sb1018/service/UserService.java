package edu.du.sb1018.service;

import edu.du.sb1018.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    User findUser(String id , String password);
    void updateUser(User user);
    void deleteUser(int uid);
    void saveUser(User user);
}
