package edu.du.sb1018.controller;

import edu.du.sb1018.entity.Board;
import edu.du.sb1018.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    final BoardServiceImpl boardService;

    @GetMapping("/")
    public String index() {
        return "redirect:/board/openBoardList.do";
    }

    @GetMapping("/board/openBoardList.do")
    public String boardList(Model model) {
        List<Board> boards = boardService.selectAllBoard();
        System.out.println(boards);
        model.addAttribute("list",boards);
        return "/board/boardList";
    }

    @GetMapping("/board/openBoardDetail.do")
    public String openBoardDetail(@RequestParam int boardIdx, Model model) {
        boardService.hitCount(boardIdx);
        Board board = boardService.selectById(boardIdx);
        model.addAttribute("board",board);
        return "/board/boardDetail";
    }

    @GetMapping("/board/openBoardWrite.do")
    public String openBoardWrite(){
        return "/board/boardWrite";
    }

    @PostMapping("/board/updateBoard.do")
    public String updateBoard(@RequestParam int boardIdx,@RequestParam String title,@RequestParam String contents) {
        boardService.updateBoard(boardIdx,title,contents);
        return "redirect:/board/openBoardList.do";
    }

    @PostMapping("/board/deleteBoard.do")
    public String deleteBoard(@RequestParam int boardIdx) {
        boardService.deleteBoard(boardIdx);
        return "redirect:/board/openBoardList.do";
    }

    @PostMapping("/board/insertBoard.do")
    public String insertBoard(@RequestParam String title,@RequestParam String contents) {
        boardService.insertBoard(contents , title);
        return "redirect:/board/openBoardList.do";
    }
}
