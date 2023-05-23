package com.dailynovel.dailynovelapi.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

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

		if(member.getPassword()==null){
			String password = UUID.randomUUID().toString().substring(0, 16);
			member.setPassword(password);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String birthdayString = dateFormat.format(member.getBirthday());
			LocalDate birthday = LocalDate.parse(birthdayString, formatter);
			member.setBirthday(java.sql.Date.valueOf(birthday));
		} catch (DateTimeParseException e) {
			e.printStackTrace();
		}
        String password = PasswordEncoder(member.getPassword());
        member.setPassword(password);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        member.setTimestamp(timestamp);
        String emailCheck =repository.saveAndFlush(member).getEmail();
        if(emailCheck==null){
            return false;
        }
        return true;
    }

    @Override
    public String FindSamephoneNumber(String phoneNumber) {
        Member member  = repository.findByPhoneNumber(phoneNumber);

        if(member!=null){
            return member.getEmail();
        }
        return null;
    }

}
