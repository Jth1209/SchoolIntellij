package edu.du.sb1021_2.service;


import edu.du.sb1021_2.dao.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.selectByEmail(username);

        UserDetails user = User.withUsername(username).password(passwordEncoder().encode(member.getPassword())).roles(member.getRole()).build();

        return user;
    }

    private PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
}
