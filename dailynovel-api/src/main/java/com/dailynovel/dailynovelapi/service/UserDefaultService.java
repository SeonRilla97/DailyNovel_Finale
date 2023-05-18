package com.dailynovel.dailynovelapi.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.dailynovel.dailynovelapi.entity.Member;
import com.dailynovel.dailynovelapi.repository.MemberRepository;

@Service
public class UserDefaultService implements UserService {

    @Autowired
    private PasswordEncoder encoder;



    @Autowired
    private MemberRepository repository;



    @Override
    public boolean FindSameNickname(String nickname) {
        Member member  = repository.findByNickName(nickname);
        if(member!=null){
            return true;
        }
        return false;
    }

    @Override
    public String PasswordEncoder(String password) {
        password = encoder.encode(password);
        return password;

    }

    @Override
    public boolean  signup(String nickname, String password, String email, String phoneNumber) {
        if(FindSameNickname(nickname)){
            return false;
        }
        else if(FindSameEmail(email)){
            return false;
        }

        password = PasswordEncoder(password);

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Member member = new Member();
        member.setEmail(email);
        member.setNickName(nickname);
        member.setPassword(password);
        member.setPhoneNumber(phoneNumber);
        member.setTimestamp(timestamp);
        String a =repository.saveAndFlush(member).getEmail();
        if(a==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean FindSameEmail(String email) {
        Member member  = repository.findByEmail(email);
        if(member!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean signup(Member member) {

        if(FindSameNickname(member.getNickName())){
            return false;
        }
        else if(FindSameEmail(member.getEmail())){
            return false;
        }
        System.out.println(member.getEmail());
        System.out.println(member.getNickName());
        System.out.println(member.getBirthday());
       String password = PasswordEncoder(member.getPassword());
       member.setPassword(password);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        member.setTimestamp(timestamp);
        String a =repository.saveAndFlush(member).getEmail();
        if(a==null){
            return false;
        }
        return true;
    }

}
