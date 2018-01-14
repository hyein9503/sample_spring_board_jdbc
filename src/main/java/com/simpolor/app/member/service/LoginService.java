package com.simpolor.app.member.service;

import com.simpolor.app.member.dao.LoginDAO;
import com.simpolor.app.member.service.LoginService;
import com.simpolor.app.member.vo.MemberVO;


public class LoginService {
	
	private LoginDAO loginDAO;

	public LoginService(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	public MemberVO selectMemberLogin(String member_id, String member_pw) {
		
		MemberVO memberVO = null;
		memberVO = loginDAO.selectMemberLogin(member_id, member_pw);
		
		return memberVO;
		
	}

}
