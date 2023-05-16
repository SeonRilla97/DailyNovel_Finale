package com.dailynovel.dailynovelapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.Member;
import com.dailynovel.dailynovelapi.service.MemberService;

@RestController
@RequestMapping("members")
public class MemberController {

    // 로그인 부분
    @Autowired
    private MemberService service;

    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> isValid(String email, String password) {
        System.out.println(email + password);
        Map<String, Object> dto = new HashMap<>();
        dto.put("result", false);
        if (service.isValid(email, password)) {
            Member member = service.getByEmail(email);
            dto.put("result", member);
            if (email == "newlec@gmail.com")
                dto.put("roles", new String[] { "ADMIN", "MEMBER" });
            else
                dto.put("roles", new String[] { "MEMBER" });
        }
        return new ResponseEntity<Map<String, Object>>(dto, HttpStatus.OK);
    }

    @PostMapping("test")
    public void test() {

        System.out.println(

        );
    }

}
