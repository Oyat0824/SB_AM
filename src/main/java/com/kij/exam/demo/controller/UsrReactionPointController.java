package com.kij.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kij.exam.demo.service.ArticleService;
import com.kij.exam.demo.service.ReactionPointService;
import com.kij.exam.demo.util.Utility;
import com.kij.exam.demo.vo.Article;
import com.kij.exam.demo.vo.ReactionPoint;
import com.kij.exam.demo.vo.ResultData;
import com.kij.exam.demo.vo.Rq;

@Controller
public class UsrReactionPointController {
	// 인스턴스 변수
	private ReactionPointService reactionPointService;
	private ArticleService articleService;
	private Rq rq;

	// 생성자 주입
	@Autowired
	public UsrReactionPointController(ReactionPointService reactionPointService, ArticleService articleService, Rq rq) {
		this.reactionPointService = reactionPointService;
		this.articleService = articleService;
		this.rq = rq;
	}

	// 리액션 정보 조회
	@RequestMapping("/usr/reactionPoint/getReactionPoint")
	@ResponseBody
	public ResultData<ReactionPoint> getReactionPoint(int id, String relTypeCode) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다.");
		}

		ReactionPoint reactionPoint = reactionPointService.getReactionPoint(rq.getLoginedMemberId(), relTypeCode, id);

		return ResultData.from("S-1", "리액션 정보 조회 성공", "reactionPoint", reactionPoint);
	}

	// 리액션 버튼
	@RequestMapping("/usr/reactionPoint/doReactionPoint")
	@ResponseBody
	public String doReactionPoint(int id, String relTypeCode, int point) {
		// 게시글 검증
		Article article = articleService.getArticle(id);

		if (article == null) {
			return Utility.jsHistoryBack("해당 게시물은 존재하지 않습니다!");
		}

		// 잘못된 relTypeCode 검증
		if (!relTypeCode.equals("article")) {
			return Utility.jsHistoryBack("잘못된 방식으로 접근하셨습니다!");
		}
		
		// 중복 리액션 체크용 변수
		ReactionPoint reactionPoint = reactionPointService.getReactionPoint(rq.getLoginedMemberId(), relTypeCode, id);

		// 포인트 검사
		if (point > 0) {
			if (reactionPoint.getPointSum() > 0) {
				return Utility.jsHistoryBack("이미 좋아요를 누른 상태입니다!");
			} else {
				reactionPointService.delReactionPoint(rq.getLoginedMemberId(), relTypeCode, id);
			}

			point = 1;

			reactionPointService.doReactionPoint(rq.getLoginedMemberId(), id, relTypeCode, point);

			return Utility.jsReplace("좋아요를 눌렀어요!", Utility.f("../article/detail?id=%d", id));
		} else if(point < 0) {
			if (reactionPoint.getPointSum() < 0) {
				return Utility.jsHistoryBack("이미 싫어요를 누른 상태입니다!");
			} else {
				reactionPointService.delReactionPoint(rq.getLoginedMemberId(), relTypeCode, id);
			}

			point = -1;

			reactionPointService.doReactionPoint(rq.getLoginedMemberId(), id, relTypeCode, point);

			return Utility.jsReplace("싫어요를 눌렀어요!", Utility.f("../article/detail?id=%d", id));
		} else {
			return Utility.jsHistoryBack("잘못된 방식으로 접근하셨습니다!");
		}
	}

	// 리액션 취소 버튼
	@RequestMapping("/usr/reactionPoint/delReactionPoint")
	@ResponseBody
	public String delReactionPoint(int id, String relTypeCode, int point) {
		// 게시글 검증
		Article article = articleService.getArticle(id);

		if (article == null) {
			return Utility.jsHistoryBack("해당 게시물은 존재하지 않습니다!");
		}

		// 잘못된 relTypeCode 검증
		if (!relTypeCode.equals("article")) {
			return Utility.jsHistoryBack("잘못된 방식으로 접근하셨습니다!");
		}

		// 리액션을 취할 수 없을 경우
		ReactionPoint reactionPoint = reactionPointService.getReactionPoint(rq.getLoginedMemberId(), relTypeCode, id);

		if (reactionPoint.getPointSum() == 0) {
			return Utility.jsHistoryBack("잘못된 방식으로 접근하셨습니다!");
		}

		if (point > 0) {
			reactionPointService.delReactionPoint(rq.getLoginedMemberId(), relTypeCode, id);

			return Utility.jsReplace("좋아요를 취소 했어요!", Utility.f("../article/detail?id=%d", id));
		} else {
			reactionPointService.delReactionPoint(rq.getLoginedMemberId(), relTypeCode, id);

			return Utility.jsReplace("싫어요를 취소 했어요!", Utility.f("../article/detail?id=%d", id));
		}
	}
}