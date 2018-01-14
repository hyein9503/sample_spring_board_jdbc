package com.simpolor.app.member.service;

import org.springframework.transaction.annotation.Transactional;

import com.simpolor.app.member.dao.WithdrawDAO;
import com.simpolor.app.member.vo.MemberVO;

public class WithdrawService {
	
	private WithdrawDAO withdrawDAO;

	public WithdrawService(WithdrawDAO withdrawDAO) {
		this.withdrawDAO = withdrawDAO;
	}
	
	@Transactional
	public int deleteMember(MemberVO memberVO) {
		
		int result = 0;
		result = withdrawDAO.deleteMember(memberVO);
		
		return result;
		
	}
	

}
