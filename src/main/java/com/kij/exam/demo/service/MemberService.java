package com.kij.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kij.exam.demo.repository.MemberRepository;

@Service
public class MemberService {
	// 인스턴스 변수
	private MemberRepository memberRepository;

	// 생성자 주입
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

// 서비스 메서드
	// 회원가입
	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
//		memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
	}

}