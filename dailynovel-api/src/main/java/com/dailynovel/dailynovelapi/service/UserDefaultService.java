package com.dailynovel.dailynovelapi.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.dailynovel.dailynovelapi.entity.Member;
import com.dailynovel.dailynovelapi.repository.MemberRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserDefaultService implements UserService {

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
    public boolean mailCheck(String email, String authCode, String Subject, String Text) {
        Member member = repository.findByEmail(email);
        if (member.getId() != null) {
            MimeMessage message = sender.createMimeMessage();
            // use the true flag to indicate you need a multipart message
            MimeMessageHelper helper;
            try {
                helper = new MimeMessageHelper(message, false);
                helper.setTo(email);
                helper.setSubject("DailyNovel" + Subject);
                // use the true flag to indicate the text included is HTML
                helper.setText("<html><body>" + Text + ":" + authCode + "</body></html>", true);
                // let's include the infamous windows Sample file (this time copied to c:/)
                sender.send(message);
            } catch (MessagingException e) {

                e.printStackTrace();
            }
            return true;
        }
        return false;

    }

    @Override
    public int FindSameNickname(String nickname) {
        int samenicknameNumber=0;
        Member member = repository.findByNickname(nickname);
        if(member !=null)

        samenicknameNumber = member.getId();

        return samenicknameNumber;

    }

    @Override
    public String PasswordEncoder(String password) {
        password = encoder.encode(password);
        return password;

    }

    @Override
    public  signup(int id, String nickname, String password, String email, String phoneNumber) {

        Member member = new Member();
        member.setEmail(email);
        member.setNickname(nickname);
        member.setPassword(password);
        repository.save(member);
        return 
    }

}
