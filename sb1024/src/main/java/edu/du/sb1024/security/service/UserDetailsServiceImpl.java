package edu.du.sb1024.security.service;


import edu.du.sb1024.entity.Member;
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

        return toUserDetails(member);
    }

    private PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    private UserDetails toUserDetails(Member member){
        return User.builder().username(member.getName()).password(member.getPassword()).roles(member.getRole()).build();
    }
}
