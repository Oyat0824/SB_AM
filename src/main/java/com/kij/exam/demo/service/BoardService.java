package com.kij.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kij.exam.demo.repository.BoardRepository;
import com.kij.exam.demo.vo.Board;

@Service
public class BoardService {
	// 인스턴스 변수
	private BoardRepository boardRepository;

	// 생성자 주입
	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public Board getBoardById(int boardId) {
		return boardRepository.getBoardById(boardId);
	}
}
