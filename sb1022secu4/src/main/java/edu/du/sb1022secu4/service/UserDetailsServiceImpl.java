package edu.du.sb1022secu4.service;

import edu.du.sb1022secu4.entity.Member;
import edu.du.sb1022secu4.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("====================>사용자 : " + username);
        Member m = memberRepository.selectByEmail(username);
        log.info(m);
        UserDetails user = User.withUsername(username).password(bCryptPasswordEncoder().encode(m.getPassword())).roles("USER").build();
        return user;
    }
    private PasswordEncoder bCryptPasswordEncoder() {return new BCryptPasswordEncoder();}
}
