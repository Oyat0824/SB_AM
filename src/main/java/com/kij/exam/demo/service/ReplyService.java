package com.kij.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kij.exam.demo.repository.ReplyRepository;
import com.kij.exam.demo.vo.Article;
import com.kij.exam.demo.vo.Reply;
import com.kij.exam.demo.vo.ResultData;

@Service
public class ReplyService {
	// 인스턴스 변수
	private ReplyRepository replyRepository;

	// 생성자 주입
	@Autowired
	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	// 댓글 작성
	public void writeReply(int loginedMemberId, String relTypeCode, int relId, String body) {
		replyRepository.writeReply(loginedMemberId, relTypeCode, relId, body);
	}

	// 댓글 목록
	public List<Reply> getForPrintReplies(int loginedMemberId, String relTypeCode, int relId) {
		List<Reply> replies = replyRepository.getForPrintReplies(relTypeCode, relId);
		
		for(Reply reply : replies) {
			actorCanChangeData(loginedMemberId, reply);
		}
		
		return replies;
	}

	// 검증
	private void actorCanChangeData(int loginedMemberId, Reply reply) {
		if (reply == null) {
			return;
		}

		ResultData actorCanChangeDataRd = actorCanMD(loginedMemberId, reply);
		reply.setActorCanChangeData(actorCanChangeDataRd.isSuccess());
	}

	public ResultData actorCanMD(int loginedMemberId, Reply reply) {
		if (reply == null) {
			return ResultData.from("F-1", "해당 댓글은 존재하지 않습니다.");
		}

		if (reply.getMemberId() != loginedMemberId) {
			return ResultData.from("F-B", "해당 댓글에 대한 권한이 없습니다.");
		}

		return ResultData.from("S-1", "가능");
	}

	// 댓글 확인
	public Reply getReply(int id) {
		return replyRepository.getReply(id);
	}

	// 댓글 삭제
	public void deleteReply(int id) {
		replyRepository.deleteReply(id);
	}

}
