package com.dailynovel.dailynovelapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.Member;
import com.dailynovel.dailynovelapi.mbentity.MbMember;
import com.dailynovel.dailynovelapi.service.MemberService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("members")
public class MemberController {

    // 로그인 부분
    @Autowired
    private MemberService service;


    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> isValid(String email, String password) {
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
        System.out.println(dto);
        return new ResponseEntity<Map<String, Object>>(dto, HttpStatus.OK);
    }

    @GetMapping("info")
    public Member viewMemberInfo(@RequestParam int id) {
        Optional<Member> test = service.viewMemberInfo(id);
        System.out.println(test.get());

        return test.get();
    }

    @PostMapping("loginAuth")
    public ResponseEntity<Map<String, Object>> isValid(String email) {
        System.out.println(email);
        Map<String, Object> dto = new HashMap<>();
        dto.put("result", false);
        if (service.isValidLoginAuth(email)) {
            Member member = service.getByEmail(email);
            dto.put("result", member);
            if (email == "newlec@gmail.com")
                dto.put("roles", new String[] { "ADMIN", "MEMBER" });
            else
                dto.put("roles", new String[] { "MEMBER" });
        }
        return new ResponseEntity<Map<String, Object>>(dto, HttpStatus.OK);
    }

    @GetMapping("follow")
    public List<Member> follow(int id) {
        List<Member> list = new ArrayList<>(); // ArrayList를 사용하여 List<Member> 생성
        list =service.getMemberFollow(id);
        return list;
    }


    @PostMapping("passwordChange")
    public boolean passwordChange(@RequestBody Member member) {
        System.out.println(member.getEmail());
        System.out.println(member.getPassword());
        boolean result = service.ChangePassword(member.getEmail(),member.getPassword());

        return result;
    }

    @PostMapping("passwordNoChange")
    public boolean passwordNoChange(@RequestBody Member member) {

        boolean result = service.ChangeNoPassword(member.getEmail());

        return result;
    }

    

    @GetMapping("mInfo")
    public MbMember getMemberInfoById(@RequestParam int id){
        return service.getMemberInfo(id);
    }


}
