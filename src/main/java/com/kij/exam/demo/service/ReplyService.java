package com.kij.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kij.exam.demo.repository.ReplyRepository;
import com.kij.exam.demo.vo.Reply;

@Service
public class ReplyService {
	// 인스턴스 변수
	private ReplyRepository replyRepository;

	// 생성자 주입
	@Autowired
	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	public void writeReply(int loginedMemberId, int relId, String relTypeCode, String body) {
		replyRepository.writeReply(loginedMemberId, relId, relTypeCode, body);
	}

	public List<Reply> getForPrintReplies(String relTypeCode, int id) {
		return replyRepository.getForPrintReplies(relTypeCode, id);
	}
}
