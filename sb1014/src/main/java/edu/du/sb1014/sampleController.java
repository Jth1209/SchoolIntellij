package edu.du.sb1014;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;

@RestController
public class sampleController {

    @GetMapping("/hello")
    public String[] hello() {//배포하는 방법 : intellij 옆에 코끼리로 가서 Task/build/bootjar를 더블 클릭하면 프로젝트 파일에 build/libs 아래에 SNAPSHOT이라는 jar파일이 만들어짐. 이걸 파일에 넣어서 cmd로 실행시키면 됨.(java -jar SNAPSHOT파일 명)
        System.out.println("hello");
        return new String[]{"hello","world"};
    }
}
