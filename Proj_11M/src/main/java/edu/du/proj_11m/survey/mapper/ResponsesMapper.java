package edu.du.proj_11m.survey.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResponsesMapper {
	List<String> selectOne();
	List<String> selectTwo();
	List<String> selectThree();
	List<String> selectUserChoice(int id);
	int userCount();
}
