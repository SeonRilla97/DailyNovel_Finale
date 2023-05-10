package com.dailynovel.dailynovelapi.service;

import com.dailynovel.dailynovelapi.entity.Member;

public interface MemberService {
    boolean isValid(String email, String password);

    Member getByEmail(String username);
}
