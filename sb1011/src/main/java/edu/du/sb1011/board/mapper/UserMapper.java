package edu.du.sb1011.board.mapper;

import edu.du.sb1011.board.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int checkUser(String id, String password);

    void updateUserInfo(UserDto userDto);

    void deleteUserInfo(String id , String password);

    void insertUser(UserDto userDto);//작동

    UserDto getUserInfo(String id, String password);
}
