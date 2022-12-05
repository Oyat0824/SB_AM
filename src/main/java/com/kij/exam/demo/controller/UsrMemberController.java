package com.kij.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kij.exam.demo.service.MemberService;

@Controller
public class UsrMemberController {
	// 인스턴스 변수
	private MemberService memberService;

	// 생성자 주입
	@Autowired
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

// 액션 메서드
	// 회원가입
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		return "가입 완료!";
	}
}