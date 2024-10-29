package edu.du.sb1024.fileuploadboard.board.controller;

import edu.du.sb1024.fileuploadboard.board.dto.BoardDto;
import edu.du.sb1024.fileuploadboard.board.dto.BoardFileDto;
import edu.du.sb1024.fileuploadboard.board.service.BoardService;
import edu.du.sb1024.fileuploadboard.entity.Board;
import edu.du.sb1024.repository.BoardRepository;
import edu.du.sb1024.service.BoardRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	final BoardRepository boardRepository;
	final BoardRepositoryImpl boardRepositoryImpl;
	
	@RequestMapping("/board/openBoardList.do")
	public String openBoardList(Model model, @PageableDefault(page=0,size=10) Pageable pageable) throws Exception{
		log.info("====> openBoardList {}", "테스트");

		List<BoardDto> list = boardService.selectBoardList();
		final int start = (int) pageable.getOffset();
		final int end = Math.min((start+ pageable.getPageSize()), list.size());
		log.info("start: {}, end: {}", start, end);
		final Page<BoardDto> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
		model.addAttribute("list",page);
		log.info("total page: {}", page.getTotalPages());
		log.info("total size: {}", page.getTotalElements());
		log.info("present page number: {}",page.getNumber());
		log.info("present page size: {}",page.getSize());
		log.info("next page exist: {}", page.hasNext());
		log.info("previous page exist: {}", page.hasPrevious());
		log.info("start page(0) : {}",page.isFirst());
		return "/board/boardList";
	}
	
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite(@ModelAttribute("boards") BoardDto boardDto) throws Exception{
		return "/board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(@ModelAttribute("boards") @Valid BoardDto board, Errors errors ,MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		if(errors.hasErrors()){
			return "/board/boardWrite";
		}
		boardRepositoryImpl.insertBoard(board.getTitle(),board.getContents());
//		boardService.insertBoard(board, multipartHttpServletRequest);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx , @ModelAttribute("board") BoardDto dto) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardDetail");

		Board b1 = boardRepository.findAllByBoardIdx(boardIdx);
		boardRepositoryImpl.updateHit(boardIdx);
//		BoardDto board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", b1);
		
		return mv;
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(@Valid BoardDto board) throws Exception{
		boardRepositoryImpl.updateBoard(board.getBoardIdx(),board.getTitle(),board.getContents());
//		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		boardRepositoryImpl.deleteBoard(boardIdx);
//		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("board/downloadBoardFile.do")
	public void downloadBoardFile(@RequestParam int idx, @RequestParam int boardIdx, HttpServletResponse response) throws Exception{
		String currentPath = Paths.get("").toAbsolutePath().toString();
		System.out.println("---------------------"+currentPath);
		BoardFileDto boardFile = boardService.selectBoardFileInformation(idx, boardIdx);
		if(ObjectUtils.isEmpty(boardFile) == false) {
			String fileName = boardFile.getOriginalFileName();
			
			byte[] files = FileUtils.readFileToByteArray(new File("./src/main/resources/static"+boardFile.getStoredFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,"UTF-8")+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
}
