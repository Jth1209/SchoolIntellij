package edu.du.sb1018.controller;

import edu.du.sb1018.entity.User;
import edu.du.sb1018.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

    @GetMapping("/user/Login.do")
    public String login() {
        return "/user/loginView";
    }

    @PostMapping("/user/loginProcess")
    public String loginProcess(@RequestParam String id , @RequestParam String password , HttpSession session) {
        User user = userService.findUser(id,password);
        session.setAttribute("login",user);
        return "redirect:/board/openBoardList.do";
    }
}
