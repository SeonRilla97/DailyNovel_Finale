package com.dailynovel.dailynovelapi.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.Member;
import com.dailynovel.dailynovelapi.service.EmailVerificationService;
import com.dailynovel.dailynovelapi.service.MailCheckService;

import com.dailynovel.dailynovelapi.service.UserService;
import com.nimbusds.oauth2.sdk.ParseException;

import ch.qos.logback.core.util.SystemInfo;


@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private MailCheckService mailService;
	@Autowired
	private EmailVerificationService emailVerificationService;
	
	@PostMapping("signup")
	public boolean signup(@RequestBody Member member) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String birthdayString = dateFormat.format(member.getBirthday());
			LocalDate birthday = LocalDate.parse(birthdayString, formatter);
			member.setBirthday(java.sql.Date.valueOf(birthday));
		} catch (DateTimeParseException e) {
			e.printStackTrace();
		}
	
	
		boolean result = service.signup(member);
		return result;
	}

	@RequestMapping("nicknameCheck")
	public boolean nicknameCheck(String nickname) {

		boolean samenickname = service.FindSameNickname(nickname);

		System.out.println(samenickname);
		return samenickname;
	}

	@RequestMapping("emailCheck")
	public boolean emailCheck(String email) {
		System.out.println("이메일체크 컨트롤러 왔니");
		System.out.println(email);
		boolean result= false;
		boolean sameemail = service.FindSameEmail(email);

		if(!sameemail){

			String authCode = mailService.randNum();
			emailVerificationService.saveVerificationCode(email, authCode);
			System.out.println(authCode);
			boolean mailCheck = mailService.mailCheck(email, authCode, "회원가입 인증메일 입니다.", "이메일 인증번호:");
			result =mailCheck;
		}

		System.out.println(result);
		return result;

	}

	@RequestMapping("emailCheckAuth")
	public boolean emailCheckAuth(String email) {
		System.out.println("이메일체크 컨트롤러 왔니");
		System.out.println(email);
		boolean sameemail = service.FindSameEmail(email);
		System.out.println(sameemail);
		return !sameemail;

	}

	@RequestMapping("emailCheckNum")
	public boolean emailCheckNum(String email, String emailCheckNum) {

		return emailVerificationService.getVerificationCodeResult(email, emailCheckNum);

		
	}

}
