package com.kij.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kij.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	// 게시물 작성
	// INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = ?, `body` = ?;
	@Insert("INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = #{title}, `body` = #{body}")
	public void writeArticle(String title, String body);

	// 게시물 가져오기
	// SELECT * FROM article WHERE id = ?;
	@Select("SELECT * FROM article WHERE id = #{id}")
	public Article getArticle(int id);

	// 게시물 목록
	// SELECT * FROM article ORDER BY id DESC;
	@Select("SELECT * FROM article ORDER BY id DESC")
	public List<Article> getArticles();

	// 게시물 삭제
	// DELETE FROM article WHERE id = ?;
	@Delete("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(int id);

	// 게시물 수정
	// UPDATE article SET updateDate = NOW(), title = ?, `body` = ? WHERE id = ?;
	@Update("UPDATE article SET updateDate = NOW(), title = #{title}, `body` = #{body} WHERE id = #{id}")
	public void modifyArticle(int id, String title, String body);
	
	// SELECT LAST_INSERT_ID();
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

}