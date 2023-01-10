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
	// 회원가입 페이지
	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}

	// 회원가입
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String loginPwChk, String name, String nickname,
			String cellphoneNum, String email) {
		// 유효성 검사
		if (Utility.empty(loginId)) {
			return Utility.jsHistoryBack("아이디를 입력해주세요!");
		}
		if (Utility.empty(loginPw)) {
			return Utility.jsHistoryBack("비밀번호를 입력해주세요!");
		}
		if (Utility.empty(loginPwChk)) {
			return Utility.jsHistoryBack("비밀번호 확인을 입력해주세요!");
		}
		if (Utility.empty(name)) {
			return Utility.jsHistoryBack("이름을 입력해주세요!");
		}
		if (Utility.empty(nickname)) {
			return Utility.jsHistoryBack("닉네임을 입력해주세요!");
		}
		if (Utility.empty(cellphoneNum)) {
			return Utility.jsHistoryBack("전화번호를 입력해주세요!");
		}
		if (Utility.empty(email)) {
			return Utility.jsHistoryBack("이메일을 입력해주세요!");
		}

		if (loginPw.equals(loginPwChk) == false) {
			return Utility.jsHistoryBack("비밀번호와 비밀번호 확인 부분이 일치하지 않습니다!");
		}

		ResultData<Integer> doJoinRd = memberService.doJoin(loginId, Utility.sha256(loginPw), name, nickname, cellphoneNum, email);

		if (doJoinRd.isFail()) {
			return Utility.jsHistoryBack(doJoinRd.getMsg());
		}

		Member member = memberService.getMemberById((int) doJoinRd.getData1());

		return Utility.jsReplace(Utility.f("%s님 가입을 축하드립니다.", member.getNickname()), "/");
	}

	// 회원 가져오기
	@RequestMapping("/usr/member/getLoginIdDup")
	@ResponseBody
	public ResultData<String> getLoginIdDup(String loginId) {
		if(Utility.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member != null) {
			return ResultData.from("F-2", "이미 존재하는 아이디입니다.", "loginId", loginId);
		}
		
		return ResultData.from("S-1", "사용 가능한 아이디입니다.", "loginId", loginId);
	}

	// 로그인 페이지
	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "usr/member/login";
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

		if (member.getLoginPw().equals(Utility.sha256(loginPw)) == false) {
			return Utility.jsHistoryBack("비밀번호가 일치하지 않습니다!");
		}

		rq.login(member);

		return Utility.jsReplace(Utility.f("%s님 환영합니다.", member.getNickname()), "/");
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
	@ResponseBody
	public String doCheckPassword(String loginPw) {
		// 유효성 검사
		if (Utility.empty(loginPw)) {
			return Utility.jsHistoryBack("비밀번호를 입력해주세요!");
		}

		if (rq.getLoginedMember().getLoginPw().equals(Utility.sha256(loginPw)) == false) {
			return Utility.jsHistoryBack("비밀번호가 일치하지 않습니다!");
		}

		String memberModifyAuthKey = memberService.genMemberModifyAuthKey(rq.getLoginedMemberId());

		return Utility.jsReplace("", Utility.f("modify?memberModifyAuthKey=%s", memberModifyAuthKey));
	}

	// 회원정보 수정 페이지
	@RequestMapping("/usr/member/modify")
	public String showModify(String memberModifyAuthKey) {
		if (Utility.empty(memberModifyAuthKey)) {
			return rq.jsReturnOnView("회원 수정 인증코드가 필요합니다.", true);
		}

		ResultData chkMemberModifyAuthKeyRd = memberService.chkMemberModifyAuthKey(rq.getLoginedMemberId(),
				memberModifyAuthKey);

		if (chkMemberModifyAuthKeyRd.isFail()) {
			return rq.jsReturnOnView(chkMemberModifyAuthKeyRd.getMsg(), true);
		}

		return "usr/member/modify";
	}

	// 회원정보 수정
	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(String memberModifyAuthKey, String nickname, String cellphoneNum, String email) {
		if (Utility.empty(memberModifyAuthKey)) {
			return Utility.jsHistoryBack("회원 수정 인증코드가 필요합니다.");
		}

		ResultData chkMemberModifyAuthKeyRd = memberService.chkMemberModifyAuthKey(rq.getLoginedMemberId(),
				memberModifyAuthKey);

		if (chkMemberModifyAuthKeyRd.isFail()) {
			return Utility.jsHistoryBack(chkMemberModifyAuthKeyRd.getMsg());
		}

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
	public String passwordModify(String memberModifyAuthKey) {
		if (Utility.empty(memberModifyAuthKey)) {
			return rq.jsReturnOnView("회원 수정 인증코드가 필요합니다.", true);
		}

		ResultData chkMemberModifyAuthKeyRd = memberService.chkMemberModifyAuthKey(rq.getLoginedMemberId(),
				memberModifyAuthKey);

		if (chkMemberModifyAuthKeyRd.isFail()) {
			return rq.jsReturnOnView(chkMemberModifyAuthKeyRd.getMsg(), true);
		}

		return "usr/member/passwordModify";
	}

	// 패스워드 수정
	@RequestMapping("/usr/member/doPasswordModify")
	@ResponseBody
	public String doPasswordModify(String memberModifyAuthKey, String loginPw, String loginPwChk) {
		if (Utility.empty(memberModifyAuthKey)) {
			return Utility.jsHistoryBack("회원 수정 인증코드가 필요합니다.");
		}

		ResultData chkMemberModifyAuthKeyRd = memberService.chkMemberModifyAuthKey(rq.getLoginedMemberId(),
				memberModifyAuthKey);

		if (chkMemberModifyAuthKeyRd.isFail()) {
			return Utility.jsHistoryBack(chkMemberModifyAuthKeyRd.getMsg());
		}

		if (Utility.empty(loginPw)) {
			return Utility.jsHistoryBack("새 비밀번호를 입력해주세요!");
		}
		if (Utility.empty(loginPwChk)) {
			return Utility.jsHistoryBack("새 비밀번호 확인을 입력해주세요!");
		}
		if (loginPw.equals(loginPwChk) == false) {
			return Utility.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}

		memberService.doPasswordModify(rq.getLoginedMemberId(), Utility.sha256(loginPw));

		return Utility.jsReplace("비밀번호가 수정됐습니다.", "myPage");
	}
}