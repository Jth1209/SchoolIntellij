package edu.du.sb1024.service;

import edu.du.sb1024.mapper.ResponsesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsesServiceImpl implements ResponsesService {

    @Autowired
    ResponsesMapper rm;

    @Override
    public List<String> selectOne() {
        return rm.selectOne();
    }

    @Override
    public List<String> selectTwo() {
        return rm.selectTwo();
    }

    @Override
    public List<String> selectThree() {
        return rm.selectThree();
    }

    @Override
    public List<String> selectUserChoice(int id) {
        return rm.selectUserChoice(id);
    }

    @Override
    public int userCount() {
        return rm.userCount();
    }
}
