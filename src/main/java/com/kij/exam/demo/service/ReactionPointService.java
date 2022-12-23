package com.kij.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kij.exam.demo.repository.ReactionPointRepository;
import com.kij.exam.demo.vo.ReactionPoint;

@Service
public class ReactionPointService {
	// 인스턴스 변수
	private ReactionPointRepository reactionPointRepository;

	// 생성자 주입
	@Autowired
	public ReactionPointService(ReactionPointRepository reactionPointRepository) {
		this.reactionPointRepository = reactionPointRepository;
	}

	public ReactionPoint getReactionPoint(int loginedMemberId, int id) {
		return reactionPointRepository.getReactionPoint(loginedMemberId, id);
	}

	public int doReactionPointUp(int loginedMemberId, int id) {
		return reactionPointRepository.doReactionPointUp(loginedMemberId, id);
	}

	public int doReactionPointDown(int loginedMemberId, int id) {
		return reactionPointRepository.doReactionPointDown(loginedMemberId, id);
	}
}
