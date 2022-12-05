package com.kij.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {
	// 회원가입
	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);
}