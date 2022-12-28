package com.kij.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.kij.exam.demo.service.MemberService;
import com.kij.exam.demo.util.Utility;

import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;
	private HttpServletRequest req;
	private HttpServletResponse res;
	private HttpSession session;

	public Rq(HttpServletRequest req, HttpServletResponse res, MemberService memberService) {
		this.req = req;
		this.res = res;
		this.session = req.getSession();	// 세션 넣기
		
		// 변수 초기화
		int loginedMemberId = 0;
		Member loginedMember = null;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = memberService.getMemberById(loginedMemberId);
		}

		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		
		this.req.setAttribute("rq", this);
	}
	
	// 해당 메서드는 Rq 객체의 생성을 유도함
	// 편의를 위해 BeforeActionInterceptor 에서 호출
	public void initOnBeforeActionInterceptor() { }

	public void jsPrintHistoryBack(String msg) {
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

	public String jsReturnOnView(String msg, boolean historyBack) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", historyBack);

		return "usr/common/js";
	}
}
