package com.kij.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kij.exam.demo.util.Utility;

import lombok.Getter;

public class Rq {
	@Getter
	private int loginedMemberId;
	private HttpServletRequest req;
	private HttpServletResponse res;

	public Rq(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
		
		// 세션 넣기
		HttpSession httpSession = req.getSession();
		
		// 로그인 멤버 아이디 검증
		int loginedMemberId = 0;
		
		if (httpSession.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		this.loginedMemberId = loginedMemberId;
	}

	public void jsPrintHistoryBack(String msg) throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		
		print(Utility.jsHistoryBack(msg));
	}

	private void print(String str) {
		try {
			res.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
