package com.kij.exam.demo.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Getter;

public class Rq {
	@Getter
	private int loginedMemberId;

	public Rq(HttpServletRequest req) {
		// 세션 넣기
		HttpSession httpSession = req.getSession();
		
		// 로그인 멤버 아이디 검증
		int loginedMemberId = 0;
		
		if (httpSession.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		this.loginedMemberId = loginedMemberId;
	}

}
