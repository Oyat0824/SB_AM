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
	
	// 리액션 정보 조회
	public ReactionPoint getReactionPoint(int loginedMemberId, String relTypeCode, int id) {
		return reactionPointRepository.getReactionPoint(loginedMemberId, relTypeCode, id);
	}

	// 리액션 (좋아요/싫어요)
	public void doReactionPoint(int loginedMemberId, int id, String relTypeCode, int point) {
		reactionPointRepository.doReactionPoint(loginedMemberId, id, relTypeCode, point);
	}

	// 리액션 취소 (좋아요/싫어요)
	public void delReactionPoint(int loginedMemberId, String relTypeCode, int id) {
		reactionPointRepository.delReactionPoint(loginedMemberId, relTypeCode, id);
	}

	
}
