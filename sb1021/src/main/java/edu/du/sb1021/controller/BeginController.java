package edu.du.sb1021.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BeginController {

    @GetMapping("/")
    public String index() {
        return "redirect:/sample/all";
    }

    @GetMapping("/sample/all")
    public String sample() {
        return "sample/all";
    }

    @GetMapping("/sample/admin")
    public String admin() {
        return "sample/admin";
    }
}
