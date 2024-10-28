package edu.du.sb1024.controller;

import edu.du.sb1024.entity.Member;
import edu.du.sb1024.spring.DuplicateMemberException;
import edu.du.sb1024.spring.MemberRegisterService;
import edu.du.sb1024.spring.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class RegisterController {

    @Autowired
    private MemberRegisterService memberRegisterService;

    @GetMapping("/register")
    public String root() {
        return "redirect:/register/step1";
    }

    @RequestMapping("/register/step1")
    public String handleStep1() {
        return "register/step1";
    }

    @PostMapping("/register/step2")
    public String handleStep2(
            @RequestParam(value = "agree", defaultValue = "false") Boolean agree,
            Model model) {
        if (!agree) {
            return "register/step1";
        }
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register/step2";
    }

    @GetMapping("/register/step2")
    public String handleStep2Get() {
        return "redirect:/register/step1";
    }

    @PostMapping("/register/step3")
    public String handleStep3(@Valid @ModelAttribute("registerRequest") RegisterRequest registerReq, BindingResult bindingResult) {
//        String route = "";
//        int count = memberRegisterService.checkEmail(member.getEmail());
//        if (count == 1) {
//            route = "redirect:/register/alreadyHave ";
//        } else {
//            if (member.getPassword().equals(member.getConfirmPassword())) {
//                memberRegisterService.regist(member);
//                route = "register/step3";
//            }else{
//                route = "redirect:/register/passwordError";
//            }
//        }
//        return route;
        if(bindingResult.hasErrors()) {
            return "register/step2";
        }else{
            memberRegisterService.regist(registerReq);
            return "register/step3";
        }
    }

    @GetMapping("/register/alreadyHave")
    public String handleStep3Get() {
        return "register/alreadyHave";
    }

    @GetMapping("/register/passwordError")
    public String handleStep4Get() {
        return "register/passwordDenied";
    }

}
