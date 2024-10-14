package edu.du.sb1011.board.controller;

import edu.du.sb1011.board.dto.BoardDto;
import edu.du.sb1011.board.dto.UserDto;
import edu.du.sb1011.board.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/")
	public String root(){
		return "/user/Index";
	}
	
	@GetMapping("/board/openBoardList.do")
	public String openBoardList(Model model) throws Exception {
		
		List<BoardDto> list = boardService.selectBoardList();
		model.addAttribute("list",list);
		
		return "/board/boardList";
	}
	
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception{
		return "/board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto board) throws Exception{
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@GetMapping("/board/openBoardDetail.do")
	public String openBoardDetail(@RequestParam int boardIdx , Model model) throws Exception{
		
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		model.addAttribute("board",board);
		
		return "/board/boardDetail";
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
}
