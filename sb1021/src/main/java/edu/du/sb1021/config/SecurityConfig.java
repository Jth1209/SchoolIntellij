package edu.du.sb1021.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    //intercepter = controller 내부에서 filtering , filter = controller의 외부에서 filtering

    @Bean
    PasswordEncoder passwordEncoder() {
        // BCrypt 해시 함수를 사용하여 비밀번호를 암호화하는 PasswordEncoder 빈을 생성합니다.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // "user"라는 사용자 이름과 "1234"라는 비밀번호를 가진 사용자 객체를 생성합니다.
        // 비밀번호는 passwordEncoder() 메소드를 통해 암호화됩니다.
        UserDetails user = User.withUsername("user1")
                .password(passwordEncoder().encode("1234")) // 비밀번호 암호화
                .roles("USER") // 사용자에게 "USER" 역할 부여
                .build(); // 사용자 객체 빌드

        // InMemoryUserDetailsManager를 사용하여 메모리에 사용자 정보를 저장하는 UserDetailsService 빈을 생성합니다.
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {//filter를 통해 미리 걸러내는 것. 그냥 세팅으로 생각.
        // CSRF(Cross-Site Request Forgery) 보호를 비활성화합니다.
        // CSRF 보호가 필요하지 않은 경우에만 비활성화해야 합니다.
        // 보안상의 이유로 주의해서 사용해야 합니다.
        http.csrf().disable();
        // 요청 권한 설정
        http.authorizeRequests()
                // 루트 경로("/")에 대한 접근을 모든 사용자에게 허용합니다.
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                // 그 외의 모든 요청은 인증된 사용자만 접근할 수 있도록 설정합니다.
                .anyRequest().authenticated();
        http.formLogin();
        return http.build();
    }
}
