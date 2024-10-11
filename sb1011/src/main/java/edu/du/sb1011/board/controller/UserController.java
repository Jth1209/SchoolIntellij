package edu.du.sb1011.board.controller;

import edu.du.sb1011.board.dto.UserDto;
import edu.du.sb1011.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/loginView")
    public String login(){
        return "/user/loginView";
    }

    @PostMapping("/user/loginProcess")
    public String loginProcess(HttpServletRequest request , Model model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        String rvalue = null;

        int check = userService.checkUser(id, password);
        model.addAttribute("check",check);
        System.out.println(check);
        if(check == 1){
            UserDto dto = userService.getUserInfo(id,password);
            model.addAttribute("user", dto);
            System.out.println(dto.getName());
            rvalue = "/user/Index";
        }else{
            rvalue = "/user/error";
        }
            return rvalue;
    }

    @GetMapping("/user/registerView")
    public String register(){
        return "/user/registerView";
    }

    @PostMapping("/user/registerProcess")
    public String registerProcess(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        UserDto dto = new UserDto(id, password, name, email);

        userService.insertUser(dto);

        return "/user/loginView";
    }

    @GetMapping("/user/updateView")
    public String update(){
        return "/user/updateView";
    }

    @PostMapping("/user/updateProcess")
    public String updateProcess(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        UserDto dto = new UserDto(id, password, name, email);
        userService.updateUserInfo(dto);

        return "redirect:/";
    }
    @GetMapping("/user/deleteView")
    public String delete(){
        return "/user/deleteView";
    }

    @PostMapping("/user/deleteProcess")
    public String deleteProcess(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        userService.deleteUserInfo(id,password);
        return "redirect:/user/loginView";
    }

}
