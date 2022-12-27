package com.kij.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kij.exam.demo.service.ReplyService;
import com.kij.exam.demo.util.Utility;
import com.kij.exam.demo.vo.Rq;

@Controller
public class UsrReplyController {
	// 인스턴스 변수
	private ReplyService replyService;
	private Rq rq;

	// 생성자 주입
	@Autowired
	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}

// 액션 메서드
	// 작성
	@RequestMapping("/usr/reply/doWrite") // 주소
	@ResponseBody // 실행할 몸통
	public String doWrite(int relId, String relTypeCode, String body) {
		// 잘못된 relTypeCode 검증
		if (!relTypeCode.equals("article")) {
			return Utility.jsHistoryBack("잘못된 방식으로 접근하셨습니다!");
		}

		replyService.writeReply(rq.getLoginedMemberId(), relId, relTypeCode, body);

		return Utility.jsReplace(Utility.f("댓글을 작성했습니다"), Utility.f("../article/detail?id=%d", relId));
	}
}