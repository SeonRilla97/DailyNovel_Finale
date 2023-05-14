package com.dailynovel.dailynovelapi.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.service.EmailVerificationService;
import com.dailynovel.dailynovelapi.service.MailCheckService;

import com.dailynovel.dailynovelapi.service.UserService;


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
	public boolean signup(String nickname, String password, String email,
			String phoneNumber) {
				boolean result= service.signup(nickname,password, email, phoneNumber);
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

		boolean result= false;
		boolean sameemail = service.FindSameEmail(email);

		if(!sameemail){

			String authCode = mailService.randNum();
			emailVerificationService.saveVerificationCode(email, authCode);
			System.out.println(authCode);
			boolean mailCheck = mailService.mailCheck(email, authCode, "회원가입 인증메일 입니다.", "이메일 인증번호:");
			result =mailCheck;
		}
		return result;

	}

	@RequestMapping("emailCheckNum")
	public boolean emailCheckNum(String email, String emailCheckNum) {

		return emailVerificationService.getVerificationCodeResult(email, emailCheckNum);

		
	}

}
