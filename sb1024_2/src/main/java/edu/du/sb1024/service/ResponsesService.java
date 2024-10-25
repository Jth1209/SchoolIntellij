package edu.du.sb1024.service;

import java.util.List;

public interface ResponsesService {
    List<String> selectOne();
    List<String> selectTwo();
    List<String> selectThree();
    List<String> selectUserChoice(int id);
    int userCount();
}
