package edu.du.sb1021_2.controller;

import edu.du.sb1021_2.dao.Member;
import edu.du.sb1021_2.service.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.du.sb1021_2.service.DuplicateMemberException;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    final MemberRepository memberRepository;

    @GetMapping("/")
    public String root() {
        return "redirect:/register/step1";
    }

    @RequestMapping("/register/step1")
    public String handleStep1() {
        return "/register/step1";
    }

    @PostMapping("/register/step2")
    public String handleStep2(
            @RequestParam(value = "agree", defaultValue = "false") Boolean agree,
            @ModelAttribute("Member") Member member) {
        if (!agree) {
            return "/register/step1";
        }
        return "/register/step2";
    }

    @GetMapping("/register/step2")
    public String handleStep2Get() {
        return "redirect:/register/step1";
    }

    @PostMapping("/register/step3")
    public String handleStep3(Model model, @ModelAttribute("Member") Member member) {
        try {
            if (member.getPassword().equals(member.getPasswordCheck())) {
                memberRepository.insert(member);
                Member newMember = memberRepository.selectByEmail(member.getEmail());
                System.out.println(newMember);
                model.addAttribute("Member", newMember);
                model.addAttribute("check", 1);
            } else {
                model.addAttribute("check", 0);
                model.addAttribute("error","비밀번호 확인이 틀렸습니다.");
            }
            return "/register/step3";
        } catch (DuplicateMemberException ex) {
            return "/register/step2";
        }
    }

}
