package com.simpolor.app.board.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.simpolor.app.board.dao.BoardDAO;
import com.simpolor.app.board.vo.BoardVO;

public class BoardService {
	
	private BoardDAO boardDAO;

	public BoardService(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	public List selectBoardList(BoardVO boardVO) {
		List list = null; 
		list = boardDAO.selectBoardList(boardVO);
		
		return list;
	}

	public BoardVO selectBoard(int seq) {
		BoardVO boardVO = null; 
		boardVO = boardDAO.selectBoard(seq);
		
		return boardVO;
	}

	@Transactional
	public int insertBoard(BoardVO boardVO) {
		int result = 0;
		result = boardDAO.insertBoard(boardVO);
		
		return result;
	}
	
	@Transactional
	public int updateBoard(BoardVO boardVO) {
		int result = 0;
		result = boardDAO.updateBoard(boardVO);
		
		return result;
	}
	
	@Transactional
	public int deleteBoard(int seq ) {
		int result = 0;
		result = boardDAO.deleteBoard(seq);
		
		return result;
	}
}
