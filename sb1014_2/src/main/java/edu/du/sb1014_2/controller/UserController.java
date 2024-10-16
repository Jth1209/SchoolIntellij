package edu.du.sb1014_2.controller;

import edu.du.sb1014_2.entity.User;
import edu.du.sb1014_2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Controller
@Log4j2
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/index")
    public String Index() {
        return "/user/Index";
    }

    @GetMapping("/user/loginView")
    public String loginView() {
        return "/user/loginView";
    }

    @PostMapping("/user/loginProcess")
    public String LoginProcess(@RequestParam String id, @RequestParam String password, Model model) {
        int check = userRepository.findUserCount(id, password);
        model.addAttribute("check", check);
        if (check == 1) {
            User user = userRepository.findUser(id, password);
            model.addAttribute("user", user);
        }
        return "/user/Index";
    }

    @GetMapping("/user/registerView")
    public String registerView(@ModelAttribute("register") User user,Model model) {
        model.addAttribute("form",user);
        return "/user/registerView";
    }

    @PostMapping("/user/registerProcess")
    public String register(@ModelAttribute("register") User user) {
        System.out.println(user);
        userRepository.saveAndFlush(user);
        return "redirect:/user/loginView";
    }

    @GetMapping("/user/updateView/{uid}")
    public String updateView(@ModelAttribute("update") User user,@PathVariable int uid , Model model) {
        Optional<User> info = userRepository.findById(uid);
        model.addAttribute("update", info.get());
        return "/user/updateView";
    }

    @PostMapping("/user/updateProcess")
    public String updateProcess(@ModelAttribute("update") User user) {
        userRepository.saveAndFlush(user);
        return "redirect:/user/loginView";
    }
}
