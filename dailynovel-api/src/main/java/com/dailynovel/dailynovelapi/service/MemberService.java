package com.dailynovel.dailynovelapi.service;

import java.util.List;
import java.util.Optional;

import com.dailynovel.dailynovelapi.entity.Member;
import com.dailynovel.dailynovelapi.mbentity.MbMember;

public interface MemberService {
    boolean isValid(String email, String password);

    // 들고온 이메일을 사용하는지 아니면
    // 레파지토리인지 엔티티껄 사용하는지
    // 그럼 db는 언제들고오는지
    
    Member getByEmail(String email);

    String PasswordEncoder(String password);
    boolean isValidLoginAuth(String email);

    Optional<Member> viewMemberInfo(int id);

    List<Member> getMemberFollow(int id);

    boolean ChangePassword(String email, String password);

    MbMember getMemberInfo(int id);

    boolean ChangeNoPassword(String email);

    void saveProfileImage(String newFilename, int id);

}
