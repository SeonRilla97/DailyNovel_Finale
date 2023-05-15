package com.dailynovel.dailynovelapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.Member;
import com.dailynovel.dailynovelapi.repository.MemberRepository;

@Service
public class MemberDefaultService implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Override
    public boolean isValid(String email, String password) {
        // TODO Auto-generated method stub
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
    public Member viewMemberInfo(int id) {
        return repository.findById(1);
    }

}
