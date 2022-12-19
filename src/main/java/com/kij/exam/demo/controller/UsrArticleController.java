package com.kij.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String doWrite(int boardId, String title, String body) {
		// 유효성 검사
		if (Utility.empty(title)) {
			return Utility.jsHistoryBack("제목을 입력해주세요!");
		}
		if (Utility.empty(body)) {
			return Utility.jsHistoryBack("내용을 입력해주세요!");
		}

		ResultData<Integer> writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), boardId, title, body);
		
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
	public String showList(Model model, @RequestParam(defaultValue = "1") int boardId, @RequestParam(defaultValue = "1") int page) {
		Board board = boardService.getBoardById(boardId);
		
		if(page <= 0) {
			return rq.jsReturnOnView("페이지 번호가 올바르지 않습니다.", true);
		}
		
		if(board == null) {
			return rq.jsReturnOnView("존재하지 않는 게시판입니다.", true);
		}
		
		int articlesCount = articleService.getArticlesCount(boardId);
		int itemsInAPage = 10;
		
		int pagesCount = (int) Math.ceil(articlesCount / (double) itemsInAPage);
		
		List<Article> articles = articleService.getArticles(boardId, itemsInAPage, page);
		
		int pages = 10; 						// 페이지 수
		int curPage = page / pages;				// 현재 페이지  1 / 10 = 0, 11 / 10 = 1
		if( page % pages > 0 ) ++curPage;		// curPage 전위연산자로 증가 1 ~ 10 : 1, 11 ~ 20 : 2
		int end = curPage * pages;				// 1 * 10 : 10, 2 * 10 : 20
		int from = end - (pages - 1);			// 10 - 9 : 1, 20 - 9 : 11
		end = pagesCount < end ? pagesCount : end;	// 엔드값이 더 클경우 정상적인 토탈페이지 적용

		model.addAttribute("board", board);
		model.addAttribute("boardId", boardId);
		model.addAttribute("articles", articles);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("from", from);
		model.addAttribute("end", end);

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