package com.kij.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kij.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	// 게시물 작성
	public void writeArticle(String title, String body, int memberId);

	// 게시물 가져오기
	public Article getArticle(int id);

	// 게시물 목록
	public List<Article> getArticles();

	// 게시물 삭제
	public void deleteArticle(int id);

	// 게시물 수정
	public void modifyArticle(int id, String title, String body);
	
	// 게시물 번호 가져오기
	public int getLastInsertId();

}