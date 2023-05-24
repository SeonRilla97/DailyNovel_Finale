package com.dailynovel.dailynovelapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.Member;
import com.dailynovel.dailynovelapi.entity.MemberFollow;
import com.dailynovel.dailynovelapi.entity.MemberFollowId;
import com.dailynovel.dailynovelapi.repository.MemberFollowRepository;
import com.dailynovel.dailynovelapi.repository.MemberRepository;

@Service
public class MemberDefaultService implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private MemberFollowRepository memberFollowRepository;

    @Override
    public boolean isValid(String email, String password) {
        Member member = repository.findByEmail(email);

        if (member == null)

            return false;

        else if (!BCrypt.checkpw(password, member.getPassword()))
            return false;

        return true;
    }

    @Override
    public Member getByEmail(String email) {

        return repository.findByEmail(email);
    }

    @Override
    public boolean isValidLoginAuth(String email) {

        Member member = repository.findByEmail(email);

        if (member == null)

            return false;


            return true;
    }

    public Optional<Member> viewMemberInfo(int id) {
        return repository.findById(id);

    }

    @Override
    public List<Member> getMemberFollow(int id) {
        List <Member> list = new ArrayList<>();
        List <MemberFollow> memberFindList = new ArrayList<>();
        memberFollowRepository.findByFollowedId(id);
        memberFindList  =      memberFollowRepository.findByFollowedId(id);
        for (int i =0; i<memberFindList.size();i++) {
            MemberFollow followId =  memberFindList.get(i);
            list.add(repository.findById(followId.getFollowId()).get());
        }
        
        System.out.println(list); // 또는 가져온 followId를 원하는 곳에 활용

        return list;
    }

}
