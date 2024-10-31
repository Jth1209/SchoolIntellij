package edu.du.proj_11m.board;

import edu.du.proj_11m.board.entity.BoardDto;
import edu.du.proj_11m.board.entity.BoardFileDto;
import edu.du.proj_11m.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
	

	final BoardService boardService;
//	private final AuthenticationConfiguration authenticationConfiguration;

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
	public String openBoardWrite() throws Exception{
		return "/board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		board.setCreatorId(SecurityContextHolder.getContext().getAuthentication().getName());
		boardService.insertBoard(board, multipartHttpServletRequest);
		return "redirect:/board/openBoardList.do";
	}
	
//	@RequestMapping("/board/openBoardDetail.do")
//	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception{
//		ModelAndView mv = new ModelAndView("/board/boardDetail");
//
//		BoardDto board = boardService.selectBoardDetail(boardIdx);
//		mv.addObject("board", board);
//
//		return mv;
//	}

	@RequestMapping("/board/openBoardDetail.do")
	public String openBoardDetail(@RequestParam int boardIdx , Model model) throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		BoardDto board = boardService.selectBoardDetail(boardIdx);
		model.addAttribute("board", board);
		model.addAttribute("uname", auth.getName());

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
	
	@RequestMapping("/board/downloadBoardFile.do")
	public void downloadBoardFile(@RequestParam int idx, @RequestParam int boardIdx, HttpServletResponse response) throws Exception{
		String currentPath = Paths.get("").toAbsolutePath().toString();
		System.out.println("---------------------"+currentPath);
		BoardFileDto boardFile = boardService.selectBoardFileInformation(idx, boardIdx);
		if(!ObjectUtils.isEmpty(boardFile)) {
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
