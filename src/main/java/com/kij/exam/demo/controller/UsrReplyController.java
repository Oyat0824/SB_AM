package com.kij.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kij.exam.demo.service.ReplyService;
import com.kij.exam.demo.util.Utility;
import com.kij.exam.demo.vo.Reply;
import com.kij.exam.demo.vo.ResultData;
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
	public String doWrite(String relTypeCode, int relId, String body) {
		replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);

		return Utility.jsReplace(Utility.f("댓글을 작성했습니다"), Utility.f("../article/detail?id=%d", relId));
	}

	// 삭제
	@RequestMapping("/usr/reply/doDelete") // 주소
	@ResponseBody // 실행할 몸통
	public String doDelete(int id) {
		Reply reply = replyService.getReply(id);

		ResultData actorCanMDRd = replyService.actorCanMD(rq.getLoginedMemberId(), reply);

		if (actorCanMDRd.isFail()) {
			return Utility.jsHistoryBack(actorCanMDRd.getMsg());
		}

		replyService.deleteReply(id);

		return Utility.jsReplace(Utility.f("댓글을 삭제했습니다"), Utility.f("../article/detail?id=%d", reply.getRelId() ));
	}
}