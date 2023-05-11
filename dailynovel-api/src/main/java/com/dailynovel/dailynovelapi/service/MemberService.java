package com.dailynovel.dailynovelapi.service;

import com.dailynovel.dailynovelapi.entity.Member;

public interface MemberService {
    boolean isValid(String email, String password);
    // 들고온 이메일을 사용하는지 아니면
    // 레파지토리인지 엔티티껄 사용하는지
    // 그럼 db는 언제들고오는지
    // 
    Member getByEmail(String username);
}
