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
	private HttpSession session;

	public Rq(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
		
		// 세션 넣기
		this.session = req.getSession();
		
		// 로그인 멤버 아이디 검증
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
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

	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}

}
