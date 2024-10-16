package edu.du.sb1014_2;

import edu.du.sb1014_2.entity.User;
import edu.du.sb1014_2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class Sb10142Application {

    final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Sb10142Application.class, args);
    }

//    @PostConstruct
//    public void init() {
//        User user = new User("1","2","3","4");
//        userRepository.save(user);
//    }
}
