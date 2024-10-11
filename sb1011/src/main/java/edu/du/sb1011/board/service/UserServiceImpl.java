package edu.du.sb1011.board.service;

import edu.du.sb1011.board.dto.UserDto;
import edu.du.sb1011.board.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int checkUser(String id, String password) {
        int deli = userMapper.checkUser(id,password);
        return deli;
    }

    @Override
    public UserDto getUserInfo(String id , String password) {
        UserDto userDto = userMapper.getUserInfo(id,password);
        return userDto;
    }

    @Override
    public void insertUser(UserDto userDto) {
        userMapper.insertUser(userDto);
    }

    @Override
    public void updateUserInfo(UserDto userDto) {
        userMapper.updateUserInfo(userDto);
    }

    @Override
    public void deleteUserInfo(String id, String password) {
        userMapper.deleteUserInfo(id,password);
    }

}
