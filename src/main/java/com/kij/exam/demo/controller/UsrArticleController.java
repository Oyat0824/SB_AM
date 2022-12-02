package com.kij.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kij.exam.demo.vo.Article;

@Controller
public class UsrArticleController {
	// 인스턴스 변수
	private int id;
	private List<Article> articles;
	
	// 생성자
	public UsrArticleController() {
		this.id = 1;
		this.articles = new ArrayList<>();
		
		makeTestData();
	}
	
	// 서비스 메서드
	// 테스트 데이터 10개 생성
	private void makeTestData() {
		for(int i = 1; i <= 10; i++) {
			
			String title = "제목" + i;
			String body = "내용" + i;
			
			writeArticle(title, body);
		}
	}
	
	// 게시물 작성
	private Article writeArticle(String title, String body) {
		Article article = new Article(this.id++, title, body);
		articles.add(article);
		
		return article;
	}
	
	// 게시물 가져오기
	private Article getArticle(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		
		return null;
	}
	
	// 게시물 삭제
	private void deleteArticle(int id) {
		Article article = getArticle(id);
		
		articles.remove(article);
	}
	
	// 게시물 수정
	private void modifyArticle(int id, String title, String body) {
		Article article = getArticle(id);
		
		article.setTitle(title);
		article.setBody(body);
	}
	
	// 액션 메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		return writeArticle(title, body);
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articles;
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = getArticle(id);
		
		if(article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}
		
		deleteArticle(id);
		
		return id + "번 게시물을 삭제했습니다.";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {
		Article article = getArticle(id);
		
		if(article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}
		
		modifyArticle(id, title, body);
		
		return article;
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticleAction(int id) {
		Article article = getArticle(id);
		
		if(article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}
		
		return article;
	}


	
}