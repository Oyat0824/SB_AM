package com.kij.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kij.exam.demo.service.ArticleService;
import com.kij.exam.demo.service.BoardService;
import com.kij.exam.demo.util.Utility;
import com.kij.exam.demo.vo.Article;
import com.kij.exam.demo.vo.Board;
import com.kij.exam.demo.vo.ResultData;
import com.kij.exam.demo.vo.Rq;

@Controller
public class UsrArticleController {
	// 인스턴스 변수
	private ArticleService articleService;
	private BoardService boardService;
	private Rq rq;

	// 생성자 주입
	@Autowired
	public UsrArticleController(ArticleService articleService, BoardService boardService, Rq rq) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
	}

// 액션 메서드
	// 작성
	@RequestMapping("/usr/article/doWrite") // 주소
	@ResponseBody // 실행할 몸통
	public String doWrite(String title, String body) {
		// 유효성 검사
		if (Utility.empty(title)) {
			return Utility.jsHistoryBack("제목을 입력해주세요!");
		}
		if (Utility.empty(body)) {
			return Utility.jsHistoryBack("내용을 입력해주세요!");
		}

		ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body, rq.getLoginedMemberId());
		
		int id = (int) writeArticleRd.getData1();

		return Utility.jsReplace(Utility.f("%d번 게시물을 작성했습니다", id), Utility.f("detail?id=%d", id));
	}

	// 작성 페이지
	@RequestMapping("/usr/article/write") // 주소
	public String showWrite() {
		return "/usr/article/write";
	}

	// 목록 페이지
	@RequestMapping("/usr/article/list")
	public String showList(Model model, int boardId) {
		Board board = boardService.getBoardById(boardId);
		
		if(board == null) {
			return rq.jsReturnOnView("존재하지 않는 게시판입니다.", true);
		}
		
		int articlesCount = articleService.getArticlesCount(boardId);
		
		List<Article> articles = articleService.getArticles(boardId);

		model.addAttribute("board", board);
		model.addAttribute("articles", articles);
		model.addAttribute("articlesCount", articlesCount);

		return "usr/article/list";
	}

	// 상세보기 페이지
	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id) {
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		model.addAttribute("article", article);

		return "usr/article/detail";
	}

	// 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = articleService.getArticle(id);

		ResultData actorCanMDRd = articleService.actorCanMD(rq.getLoginedMemberId(), article);

		if (actorCanMDRd.isFail()) {
			return Utility.jsHistoryBack(actorCanMDRd.getMsg());
		}

		articleService.deleteArticle(id);

		return Utility.jsReplace(Utility.f("%d번 게시물을 삭제했습니다", id), "list?boardId=1");
	}

	// 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id);

		ResultData actorCanMDRd = articleService.actorCanMD(rq.getLoginedMemberId(), article);

		if (actorCanMDRd.isFail()) {
			return Utility.jsHistoryBack(actorCanMDRd.getMsg());
		}

		articleService.modifyArticle(id, title, body);

		return Utility.jsReplace(Utility.f("%d번 게시물을 수정했습니다", id), Utility.f("detail?id=%d", id));
	}

	// 수정 페이지
	@RequestMapping("/usr/article/modify")
	public String showModify(Model model, int id) {
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		ResultData actorCanMDRd = articleService.actorCanMD(rq.getLoginedMemberId(), article);

		if (actorCanMDRd.isFail()) {
			return rq.jsReturnOnView(actorCanMDRd.getMsg(), true);
		}

		model.addAttribute("article", article);

		return "/usr/article/modify";
	}

}