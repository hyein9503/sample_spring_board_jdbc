package com.simpolor.app.member.service;

import org.springframework.transaction.annotation.Transactional;

import com.simpolor.app.member.dao.JoinDAO;
import com.simpolor.app.member.vo.MemberVO;

public class JoinService {
	
	private JoinDAO joinDAO;

	public JoinService(JoinDAO joinDAO) {
		this.joinDAO = joinDAO;
	}
	
	public int selectMemberIdDupCheck(String member_id) {
		int result = 0;
		result = joinDAO.selectMemberIdDupCheck(member_id);
		
		return result;
		
	}
	
	public int selectMemberEmailDupCheck(String email) {
		int result = 0;
		result = joinDAO.selectMemberEmailDupCheck(email);
		
		return result;
		
	}
	
	@Transactional
	public int insertJoinMember(MemberVO memberVO) {
		
		int result = 0;
		result = joinDAO.insertJoinMember(memberVO);
		
		return result;
		
	}

}
