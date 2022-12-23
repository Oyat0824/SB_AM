package com.kij.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kij.exam.demo.service.ArticleService;
import com.kij.exam.demo.service.ReactionPointService;
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
	
	// 추천 정보 조회
	@RequestMapping("/usr/reactionPoint/getReactionPoint")
	@ResponseBody
	public ResultData<ReactionPoint> getReactionPoint(int id) {
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		
		ReactionPoint reactionPoint = reactionPointService.getReactionPoint(rq.getLoginedMemberId(), id);
		
		return ResultData.from("S-1", "리액션 정보 조회 성공", "reactionPoint", reactionPoint);
	}
	
	// 좋아요 버튼
	@RequestMapping("/usr/reactionPoint/doReactionPointUp")
	@ResponseBody
	public ResultData<Integer> doReactionPointUp(int id) {
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		
		int affectedRow = reactionPointService.doReactionPointUp(rq.getLoginedMemberId(), id);
		
		return ResultData.from("S-1", "좋아요 성공", "affectedRow", affectedRow);
	}
	
	// 싫어요 버튼
	@RequestMapping("/usr/reactionPoint/doReactionPointDown")
	@ResponseBody
	public ResultData<Integer> doReactionPointDown(int id) {
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		
		int affectedRow = reactionPointService.doReactionPointDown(rq.getLoginedMemberId(), id);
		
		return ResultData.from("S-1", "싫어요 성공", "affectedRow", affectedRow);
	}
}