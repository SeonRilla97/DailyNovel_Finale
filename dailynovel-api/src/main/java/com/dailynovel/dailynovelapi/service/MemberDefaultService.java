package com.dailynovel.dailynovelapi.service;

import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.Member;

@Service
public class MemberDefaultService implements MemberService {

    @Override
    public boolean isValid(String string, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isValid'");
    }

    @Override
    public Member getByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByUsername'");
    }
    
}
