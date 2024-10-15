package edu.du.sb1011prob.controller;


import edu.du.sb1011prob.dto.Board;
import edu.du.sb1011prob.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//@RequestMapping("/board")
@Log4j2
public class BoardController {
	
	@Autowired
	private BoardRepository boardRepository;

	@GetMapping("/")
	public String root(){
		return "redirect:/board/openBoardList.do";
	}
	
	@GetMapping("/board/openBoardList.do")
	public String openBoardList(Model model) throws Exception {
		
		List<Board> boardList = boardRepository.selectList();
		log.info(boardList);
		model.addAttribute("list",boardList);
		
		return "/board/boardList";
	}
	
//	@RequestMapping("/board/openBoardWrite.do")
//	public String openBoardWrite() throws Exception{
//		return "/board/boardWrite";
//	}
//
//	@RequestMapping("/board/insertBoard.do")
//	public String insertBoard(BoardDto board) throws Exception{
//		boardService.insertBoard(board);
//		return "redirect:/board/openBoardList.do";
//	}
//
//	@GetMapping("/board/openBoardDetail.do")
//	public String openBoardDetail(@RequestParam int boardIdx , Model model) throws Exception{
//
//		BoardDto board = boardService.selectBoardDetail(boardIdx);
//		model.addAttribute("board",board);
//
//		return "/board/boardDetail";
//	}
//
//	@RequestMapping("/board/updateBoard.do")
//	public String updateBoard(BoardDto board) throws Exception{
//		boardService.updateBoard(board);
//		return "redirect:/board/openBoardList.do";
//	}
//
//	@RequestMapping("/board/deleteBoard.do")
//	public String deleteBoard(int boardIdx) throws Exception{
//		boardService.deleteBoard(boardIdx);
//		return "redirect:/board/openBoardList.do";
//	}
}
