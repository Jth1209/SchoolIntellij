package edu.du.sb1007;

import edu.du.sb1007.dao.ISimpleBbsDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {

    @Autowired
    ISimpleBbsDao Dao;

    @GetMapping("/")
    public String root() {
        return "redirect:list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", Dao.listDao());
        return "list";
    }

    @GetMapping("/view")
    public String view(Model model, HttpServletRequest request){
        String sId = request.getParameter("id");
        System.out.println(sId);
        model.addAttribute("dto",Dao.viewDao(sId));
        return "view";
    }

    @GetMapping("/writeForm")
    public String writeForm(){
        return "writeForm";
    }

    @PostMapping("/write")//get post 방식에 맞게 Mapping을 수정
    public String write(HttpServletRequest request){
        String sW = request.getParameter("writer");
        String sT = request.getParameter("title");
        String sC = request.getParameter("content");
        Dao.writeDao(sW,sT,sC);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request){
        String sId = request.getParameter("id");
        Dao.deleteDao(sId);
        return "redirect:list";
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model, HttpServletRequest request){
        String sId = request.getParameter("id");
        model.addAttribute("dto",Dao.viewDao(sId));
        return "updateForm";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request){
        String sId = request.getParameter("id");
        String sW = request.getParameter("writer");
        String sT = request.getParameter("title");
        String sC = request.getParameter("content");
        Dao.updateDao(sId,sW,sT,sC);
        return "redirect:list";
    }
}
