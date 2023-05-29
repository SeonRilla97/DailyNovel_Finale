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
import com.dailynovel.dailynovelapi.mbentity.MbDiary;
import com.dailynovel.dailynovelapi.mbrepository.MbDiaryRepository;
import com.dailynovel.dailynovelapi.repository.MemberRepository;

@Service
public class UserDefaultService implements UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MbDiaryRepository mbRepository;

    @Autowired
    private MemberRepository repository;

    @Override
    public boolean FindSameNickname(String nickname) {
        Member member = repository.findByNickName(nickname);
        if (member != null) {
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
    public boolean signup(String nickname, String password, String email, String phoneNumber) {
        if (FindSameNickname(nickname)) {
            return false;
        } else if (FindSameEmail(email)) {
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
        String a = repository.saveAndFlush(member).getEmail();

        if (a == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean FindSameEmail(String email) {
        Member member = repository.findByEmail(email);
        if (member != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean signup(Member member) {

        if (FindSameNickname(member.getNickName())) {
            return false;
        } else if (FindSameEmail(member.getEmail())) {
            return false;
        }

        if (member.getPassword() == null) {
            String password = UUID.randomUUID().toString().substring(0, 16);
            member.setPassword(password);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birthdayString = dateFormat.format(member.getBirthday());
            LocalDate birthday = LocalDate.parse(birthdayString, formatter);
            member.setBirthday(java.sql.Date.valueOf(birthday));
            member.setPasswordChangePeriod(java.sql.Date.valueOf(birthday));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        String password = PasswordEncoder(member.getPassword());
        member.setPassword(password);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        member.setTimestamp(timestamp);
        String emailCheck = repository.saveAndFlush(member).getEmail();
        
        Member newMember = repository.findByEmail(member.getEmail());

        int newMemberId = newMember.getId().intValue();

        System.out.println(newMemberId);
        String title = "[{\"insert\":\"안녕하세요!\"},{\"attributes\":{\"header\":1},\"insert\":\"\\n\"},{\"insert\":\"\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"모두의 일기장\"},{\"insert\":\"입니다.\\n따듯한 우리의 공간에 오신것을 환영해요\\n\\n모두의일기장의 사용법을 알려드려요!\\n\\n사용법\"},{\"attributes\":{\"header\":1},\"insert\":\"\\n\"},{\"insert\":\"첫번째! 일기 만들기\"},{\"attributes\":{\"header\":2},\"insert\":\"\\n\"},{\"insert\":\"왼쪽에 \"},{\"attributes\":{\"color\":\"#e60000\",\"bold\":true},\"insert\":\"+ 버튼\"},{\"insert\":\"을 눌러요!\"},{\"attributes\":{\"list\":\"ordered\"},\"insert\":\"\\n\"},{\"attributes\":{\"header\":2},\"insert\":\"\\n\"},{\"insert\":\"두번째! 기본 설정하는 법\"},{\"attributes\":{\"header\":2},\"insert\":\"\\n\"},{\"insert\":\"위쪽의 흐림은 일기를 쓴 시간의 \"},{\"attributes\":{\"color\":\"#0047b2\",\"bold\":true},\"insert\":\"날씨\"},{\"insert\":\"를 불러와요!\"},{\"attributes\":{\"list\":\"ordered\"},\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#0047b2\",\"bold\":true},\"insert\":\"태그\"},{\"insert\":\"는 내가 어떤 일기를 쓰는지 정해요!\"},{\"attributes\":{\"list\":\"ordered\"},\"insert\":\"\\n\"},{\"insert\":\"일기를 쓰는 내 \"},{\"attributes\":{\"color\":\"#0047b2\",\"bold\":true},\"insert\":\"기분\"},{\"insert\":\"이 무엇인지 찬찬히 골라주세요.\"},{\"attributes\":{\"list\":\"ordered\"},\"insert\":\"\\n\"},{\"insert\":\"이 일기를 쓰는 내 마음의 \"},{\"attributes\":{\"color\":\"#0047b2\",\"bold\":true},\"insert\":\"진심도\"},{\"insert\":\"는 어느정도인지 선택해주세요.\"},{\"attributes\":{\"list\":\"ordered\"},\"insert\":\"\\n\"},{\"insert\":\"\\n\\u001d세번째! 일기 쓰는법하는 법\"},{\"attributes\":{\"header\":2},\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#9933ff\",\"bold\":true},\"insert\":\"제목\"},{\"insert\":\"을 입력해주세요.\"},{\"attributes\":{\"list\":\"ordered\"},\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#9933ff\",\"bold\":true},\"insert\":\"여기\"},{\"insert\":\"에 입력해주세요.\"},{\"attributes\":{\"list\":\"ordered\"},\"insert\":\"\\n\"},{\"insert\":\"오른쪽 \"},{\"attributes\":{\"color\":\"#9933ff\",\"bold\":true},\"insert\":\"지도보기\"},{\"insert\":\"를 하면 내가 어디서 지도를 썼는지 볼 수 있어요.\"},{\"attributes\":{\"list\":\"ordered\"},\"insert\":\"\\n\"},{\"insert\":\"내가 \"},{\"attributes\":{\"color\":\"#9933ff\",\"bold\":true},\"insert\":\"여행\"},{\"insert\":\"한곳을 남기고 싶으면 검색을 해서 저장하면 돼요\"},{\"attributes\":{\"indent\":1,\"list\":\"ordered\"},\"insert\":\"\\n\"},{\"insert\":\"\\n네번째! 공유하는 법\"},{\"attributes\":{\"header\":2},\"insert\":\"\\n\"},{\"insert\":\"제목 오른쪽에 \"},{\"attributes\":{\"color\":\"#ff9900\",\"bold\":true},\"insert\":\"토글\"},{\"insert\":\"을 누르면 공유가 돼요!\"},{\"attributes\":{\"list\":\"ordered\"},\"insert\":\"\\n\"},{\"insert\":\"공유가 되면 \"},{\"attributes\":{\"color\":\"#ff9900\",\"bold\":true},\"insert\":\"3일\"},{\"insert\":\"동안 되어요\"},{\"attributes\":{\"list\":\"ordered\"},\"insert\":\"\\n\"},{\"insert\":\"\\n\\n\"}]";
        MbDiary mbDiary = new MbDiary(
                null, // id
                newMemberId, // member_id (동형님 부분 -> 자료형이 int라 오류뜸)
                "모두의 일기장에 오신것을 환영합니다.",
                title,
                "날씨",
                "기분",
                "진심도",
                "태그",
                null,
                37.5077079,
                127.1035681);

        int a = mbRepository.insertNew(mbDiary);
        System.out.println(a);

        if (emailCheck == null) {
            return false;
        }
        return true;
    }

    @Override
    public String FindSamephoneNumber(String phoneNumber) {
        Member member = repository.findByPhoneNumber(phoneNumber);

        if (member != null) {
            return member.getEmail();
        }
        return null;
    }

    @Override
    public boolean ChangePassword(String email, String password) {

        Member member = repository.findByEmail(email);
        if (member != null) {
            System.out.println(password);
            password = PasswordEncoder(password);
            System.out.println(password);
            member.setPassword(password);
            String passwordCheck = repository.saveAndFlush(member).getPassword();
            if (passwordCheck != password) {
                return false;
            }
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(date);
            member.setBirthday(java.sql.Date.valueOf(dateString));
            return true;
        }
        return false;
    }

}
