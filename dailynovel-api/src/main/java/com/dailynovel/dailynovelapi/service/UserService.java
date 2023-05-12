package com.dailynovel.dailynovelapi.service;

import com.dailynovel.dailynovelapi.entity.Member;

public interface UserService {

    String randNum();

    boolean mailCheck(String email, String authCode, String Subject, String Text);

    int FindSameNickname(String nickname);

    String PasswordEncoder(String password);

    int signup(int id, String nickname,String password, String email, String phoneNumber);



}
