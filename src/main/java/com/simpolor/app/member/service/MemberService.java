package com.simpolor.app.member.service;

import org.springframework.transaction.annotation.Transactional;

import com.simpolor.app.member.dao.MemberDAO;
import com.simpolor.app.member.vo.MemberVO;

public class MemberService {
	
	private MemberDAO memberDAO;

	public MemberService(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	public MemberVO selectMember(String member_id) {
		MemberVO memberVO = null; 
		memberVO = memberDAO.selectMember(member_id);
		
		return memberVO;
	}
	
	@Transactional
	public int updateMember(MemberVO memberVO) {
		int result = 0;
		result = memberDAO.updateMember(memberVO);
		
		return result;
	}
	
	@Transactional
	public int updateMemberPassword(MemberVO memberVO) {
		int result = 0;
		result = memberDAO.updateMemberPassword(memberVO);
		
		return result;
	}
	
	public int selectMemberDupCheck(String member_id, String member_pw) {
		int result = 0;
		result = memberDAO.selectMemberDupCheck(member_id, member_pw);
		
		return result;
	}

}
