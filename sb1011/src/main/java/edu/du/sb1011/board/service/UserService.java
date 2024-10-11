package edu.du.sb1011.board.service;

import edu.du.sb1011.board.dto.UserDto;


public interface UserService {
    int checkUser(String id, String password);

    UserDto getUserInfo(String id,String password);

    void insertUser(UserDto userDto);

    void updateUserInfo(UserDto userDto);

    void deleteUserInfo(String id , String password);

}
