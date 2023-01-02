package com.kij.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kij.exam.demo.service.MemberService;
import com.kij.exam.demo.util.Utility;
import com.kij.exam.demo.vo.Member;
import com.kij.exam.demo.vo.ResultData;
import com.kij.exam.demo.vo.Rq;

import ch.qos.logback.classic.pattern.Util;

@Controller
public class UsrMemberController {
	// 인스턴스 변수
	private MemberService memberService;
	private Rq rq;

	// 생성자 주입
	@Autowired
	public UsrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}

// 액션 메서드
	// 회원가입
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		// 유효성 검사
		if (Utility.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요!");
		}
		if (Utility.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요!");
		}
		if (Utility.empty(name)) {
			return ResultData.from("F-3", "이름을 입력해주세요!");
		}
		if (Utility.empty(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력해주세요!");
		}
		if (Utility.empty(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력해주세요!");
		}
		if (Utility.empty(email)) {
			return ResultData.from("F-6", "이메일을 입력해주세요!");
		}

		ResultData<Integer> doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (doJoinRd.isFail()) {
			return ResultData.from(doJoinRd.getResultCode(), doJoinRd.getMsg());
		}

		Member member = memberService.getMemberById((int) doJoinRd.getData1());

		return ResultData.from(doJoinRd.getResultCode(), doJoinRd.getMsg(), "member", member);
	}

	// 로그인
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
		// 유효성 검사
		if (Utility.empty(loginId)) {
			return Utility.jsHistoryBack("아이디를 입력해주세요!");
		}
		if (Utility.empty(loginPw)) {
			return Utility.jsHistoryBack("비밀번호를 입력해주세요!");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Utility.jsHistoryBack(Utility.f("존재하지 않는 아이디(%s)입니다.", loginId));
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return Utility.jsHistoryBack("비밀번호가 일치하지 않습니다!");
		}

		rq.login(member);

		return Utility.jsReplace(Utility.f("%s님 환영합니다.", member.getNickname()), "/");
	}

	// 로그인 페이지
	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "usr/member/login";
	}

	// 로그아웃
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {
		rq.logout();

		return Utility.jsReplace("로그아웃 완료!", "/");
	}

	// 마이 페이지
	@RequestMapping("/usr/member/myPage")
	public String showMyPage() {
		return "usr/member/myPage";
	}

	// 패스워드 확인 페이지
	@RequestMapping("/usr/member/checkPassword")
	public String showCheckPassword() {
		return "usr/member/checkPassword";
	}

	// 패스워드 확인
	@RequestMapping("/usr/member/doCheckPassword")
	public String doCheckPassword(String loginPw) {
		// 유효성 검사
		if (Utility.empty(loginPw)) {
			return rq.jsReturnOnView("비밀번호를 입력해주세요!", true);
		}

		if (rq.getLoginedMember().getLoginPw().equals(loginPw) == false) {
			return rq.jsReturnOnView("비밀번호가 일치하지 않습니다!", true);
		}

		return "usr/member/modify";
	}

	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(String nickname, String cellphoneNum, String email) {
		// 유효성 검사
		if (Utility.empty(nickname)) {
			return Utility.jsHistoryBack("닉네임을 입력해주세요!");
		}
		if (Utility.empty(cellphoneNum)) {
			return Utility.jsHistoryBack("전화번호를 입력해주세요!");
		}
		if (Utility.empty(email)) {
			return Utility.jsHistoryBack("이메일을 입력해주세요!");
		}

		memberService.doModify(rq.getLoginedMemberId(), nickname, cellphoneNum, email);

		return Utility.jsReplace("회원정보가 수정됐습니다.", "myPage");
	}

	// 패스워드 수정 페이지
	@RequestMapping("/usr/member/passwordModify")
	public String passwordModify() {
		return "usr/member/passwordModify";
	}
	
	// 패스워드 수정
	@RequestMapping("/usr/member/doPasswordModify")
	@ResponseBody
	public String doPasswordModify(String loginPw, String loginPwChk) {
		if (Utility.empty(loginPw)) {
			return Utility.jsHistoryBack("새 비밀번호를 입력해주세요!");
		}
		if (Utility.empty(loginPwChk)) {
			return Utility.jsHistoryBack("새 비밀번호 확인을 입력해주세요!");
		}
		if (loginPw.equals(loginPwChk) == false) {
			return Utility.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		
		memberService.doPasswordModify(rq.getLoginedMemberId(), loginPw);

		return Utility.jsReplace("비밀번호가 수정됐습니다.", "myPage");
	}
}