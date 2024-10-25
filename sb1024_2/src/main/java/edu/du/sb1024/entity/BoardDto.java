package edu.du.sb1024.entity;

import lombok.Data;

import java.util.List;

@Data
public class BoardDto {
	
	private int boardIdx;
	
	private String title;
	
	private String contents;
	
	private int hitCnt;
	
	private String creatorId;
	
	private String createdDatetime;
	
	private String updaterId;
	
	private String updatedDatetime;

	private String uname;

	private List<BoardFileDto> fileList;
}
