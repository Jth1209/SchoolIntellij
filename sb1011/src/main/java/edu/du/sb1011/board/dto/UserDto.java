package edu.du.sb1011.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserDto {
    private int uid;
    private String id;
    private String password;
    private String name;
    private String email;

    public UserDto(String id, String password, String name, String email) {//insert용
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public UserDto(String password, String name, String email) {//update용
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
