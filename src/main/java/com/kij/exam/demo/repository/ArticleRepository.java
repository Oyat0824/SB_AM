package com.kij.exam.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kij.exam.demo.vo.Article;

@Component
public class ArticleRepository {
	// 인스턴스 변수
	private int id;
	private List<Article> articles;

	// 생성자
	public ArticleRepository() {
		this.id = 1;
		this.articles = new ArrayList<>();
	}

	// 테스트 데이터 10개 생성
	public void makeTestData() {
		for (int i = 1; i <= 10; i++) {

			String title = "제목" + i;
			String body = "내용" + i;

			writeArticle(title, body);
		}
	}

	// 게시물 작성
	public Article writeArticle(String title, String body) {
		Article article = new Article(this.id++, title, body);
		articles.add(article);

		return article;
	}

	// 게시물 가져오기
	public Article getArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}

		return null;
	}

	// 게시물 삭제
	public void deleteArticle(int id) {
		Article article = getArticle(id);

		articles.remove(article);
	}

	// 게시물 수정
	public void modifyArticle(int id, String title, String body) {
		Article article = getArticle(id);

		article.setTitle(title);
		article.setBody(body);
	}
	
	// 게시물 목록
	public List<Article> getArticles() {
		return articles;
	}
}