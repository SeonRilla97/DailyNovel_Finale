package com.dailynovel.dailynovelapi.service;

import com.dailynovel.dailynovelapi.entity.Member;

public interface MemberService {
    boolean isValid(String string, String password);

    Member getByUsername(String username);
}
