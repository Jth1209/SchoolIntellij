package edu.du.sb1014;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController2 {

    @Autowired
    MemoRepository memoRepository;

    @GetMapping("/ex1")
    public void ex1(Model model) {
        IntStream.range(1,10).forEach(i->{
            Memo memo = Memo.builder().text("샘플"+i).build();
            memoRepository.save(memo);
        });

        for(Memo memo : memoRepository.findAll()) {
            log.info(memo.toString());
        }
        model.addAttribute("list", memoRepository.findAll());
        model.addAttribute("test",memoRepository.getByid(3L));
    }
}
