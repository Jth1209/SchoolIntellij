package edu.du.sb1015_2.controller;

import edu.du.sb1015_2.entity.MyData;
import edu.du.sb1015_2.repository.MyDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MyDataController {

    final MyDataRepository myDataRepository;

//    @GetMapping("/")
//    public String index(Model model){
//        Optional<MyData> myData = myDataRepository.findById(1L);
//        model.addAttribute("formMethod",myData);
//        return "index";
//    }

    @RequestMapping("/")
    public String index(@ModelAttribute("formModel") MyData myData , Model model) {
//        model.addAttribute("msg", "");
        List<MyData> list = myDataRepository.findAll();
        model.addAttribute("datalist", list);
        return "index";
    }

    @PostMapping("/")
    public String add(@ModelAttribute("formModel") MyData myData){
        myDataRepository.save(myData);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute("formModel") MyData myData,@PathVariable Long id,Model model){
        Optional<MyData> data = myDataRepository.findById(id);
        model.addAttribute("formModel",data.get());
        return "edit";
    }

    @Transactional
    @PostMapping("/edit")
    public String update(@ModelAttribute("formModel") MyData myData){
        myDataRepository.saveAndFlush(myData);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@ModelAttribute("formModel") MyData myData,@PathVariable Long id,Model model){
        Optional<MyData> data = myDataRepository.findById(id);
        model.addAttribute("formModel",data.get());
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("formModel") MyData myData){
        myDataRepository.delete(myData);
        return "redirect:/";
    }
}
