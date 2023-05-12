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

    @Autowired
    private FeelingRepository feelingRepository;

    @Autowired
    private HonestyRepository honestyRepository;

    @Autowired
    private TagRepository tagRepository;

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
    public List<diary> getByFeeling(Integer memberId){
        return feelingRepository.findByFeeling(memberId);
    }

    @Override
    public List<diary> getByHonesty(Integer memberId) {
        // TODO Auto-generated method stub
        return honestyRepository.findByHonesty(memberId);
    }


    @Override
    public List<diary> getByTag(Integer memberId) {
        // TODO Auto-generated method stub
        return tagRepository.findByTag(memberId);
    }


    @Override
    public Member getByEmail(String email) {

        return repository.findByEmail(email);
    }

}
