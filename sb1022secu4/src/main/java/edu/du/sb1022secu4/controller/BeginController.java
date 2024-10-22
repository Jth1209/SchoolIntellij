package edu.du.sb1022secu4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BeginController {

    @GetMapping("/")
    public String index() {
        return "redirect:/sample/all";
    }

    @GetMapping("/sample/all")
    public String sample() {
        return "/sample/all";
    }

    @GetMapping("/sample/admin")
    public String admin() {
        return "/sample/admin";
    }

    @GetMapping("/sample/member")
    public String member() {
        return "/sample/member";
    }
}
