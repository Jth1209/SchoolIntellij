package edu.du.sb1024.fileuploadboard.board.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BoardDto {
	
	private int boardIdx;

//	@NotEmpty(message="공백이나 빈칸으로 제출할 수 없습니다. 내용을 입력해주세요.")
	@NotBlank(message="공백이나 빈칸으로 제출할 수 없습니다. 내용을 입력해주세요.")
	private String title;
//	@NotEmpty(message="공백이나 빈칸으로 제출할 수 없습니다. 내용을 입력해주세요.")
	@NotBlank(message="공백이나 빈칸으로 제출할 수 없습니다. 내용을 입력해주세요.")
	private String contents;
	
	private int hitCnt;
	
	private String creatorId;
	
	private String createdDatetime;
	
	private String updaterId;
	
	private String updatedDatetime;
	
	private List<BoardFileDto> fileList;
}
