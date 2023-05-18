package com.dailynovel.dailynovelapi.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dailynovel.dailynovelapi.entity.Member;
import com.dailynovel.dailynovelapi.repository.MemberRepository;

public class DailyNovelUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        DailyNovelUserDetails user = new DailyNovelUserDetails();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        Member  member = repository.findByEmail(email);
        user.setId(member.getId());
        user.setEmail(member.getEmail());
        user.setPassword(member.getPassword());
        user.getAuthorities(authorities);

        return user;

    }
    
    
}