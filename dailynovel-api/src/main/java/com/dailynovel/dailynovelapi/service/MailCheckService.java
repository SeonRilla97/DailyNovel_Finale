package com.dailynovel.dailynovelapi.service;

public interface MailCheckService {
    String randNum();

    boolean mailCheck(String email, String authCode, String Subject, String Text);
}
