package com.dailynovel.dailynovelapi.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.dailynovel.dailynovelapi.entity.Member;
import com.dailynovel.dailynovelapi.repository.MemberRepository;

@Service
public class UserDefaultService  implements UserService {

    @Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JavaMailSenderImpl sender;	

    @Autowired
    private MemberRepository repository;

    @Override
    public String randNum() {
		Random rand = new Random();
		String randNum = Integer.toString(rand.nextInt(999999));
	
		return randNum;

    }

    @Override
    public boolean mailCheck(String email, String authCode, String string, String string2) {
   

    }

    @Override
    public int FindSameNickname(String nickname) {
     

    }

    @Override
    public String PasswordEncoder(String password) {
   

    }

    @Override
    public void signup(String password, String email, String phoneNumber) {


    }

    @Override
    public Member SetMember(String username, String password, String email, String phoneNumber, int sex) {


    }
    
}
