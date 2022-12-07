package com.kij.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kij.exam.demo.repository.ArticleRepository;
import com.kij.exam.demo.util.Utility;
import com.kij.exam.demo.vo.Article;
import com.kij.exam.demo.vo.ResultData;

@Service
public class ArticleService {
	// 인스턴스 변수
	private ArticleRepository articleRepository;

	// 생성자 주입
	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

// 서비스 메서드
	// 게시물 작성
	public ResultData writeArticle(String title, String body) {
		articleRepository.writeArticle(title, body);
		
		int id = articleRepository.getLastInsertId();
		
		return ResultData.from("S-1", Utility.f("%d번 게시물이 생성되었습니다.", id), id);
	}

	// 게시물 가져오기
	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}

	// 게시물 목록
	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	// 게시물 삭제
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	// 게시물 수정
	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}

}