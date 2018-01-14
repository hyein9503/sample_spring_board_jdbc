package com.simpolor.app.member.service;

import org.springframework.transaction.annotation.Transactional;

import com.simpolor.app.member.dao.SearchDAO;
import com.simpolor.app.member.vo.MemberVO;

public class SearchService {
	
	private SearchDAO searchDAO;

	public SearchService(SearchDAO searchDAO) {
		this.searchDAO = searchDAO;
	}
	
	public MemberVO selectMemberSearch(String member_name, String email) {
		
		MemberVO memberVO = null;
		memberVO = searchDAO.selectMemberSearch(member_name, email);
		
		return memberVO;
		
	}
	
	@Transactional
	public int updateMemberChange(MemberVO memberVO) {
		
		int result = 0;
		result = searchDAO.updateMemberChange(memberVO);
		
		return result;
		
	}
	
	
}
