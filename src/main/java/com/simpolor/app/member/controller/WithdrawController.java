package com.simpolor.app.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.member.service.WithdrawService;
import com.simpolor.app.member.vo.MemberVO;

@Controller
public class WithdrawController {
	
	private WithdrawService withdrawService;
	
	public void setWithdrawService(WithdrawService withdrawService) {
		this.withdrawService = withdrawService;
	}
	
	// 회원탈퇴 폼
	@RequestMapping(value = "/member/withdraw", method = RequestMethod.GET)
	public String withdraw( RedirectAttributes redirectAttributes, HttpSession session, Model model ) {
		
		String session_member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		
		if("".equals(session_member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", "로그인이 필요합니다.");
			return "redirect:/home";
		}
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(session_member_id);
		
		model.addAttribute("memberVO", memberVO);

		return "/app/member/withdraw";
	}
	
	// 회원탈퇴 처리
	@RequestMapping(value = "/member/withdraw", method = RequestMethod.POST)
	public String withdraw( RedirectAttributes redirectAttributes, HttpSession session, Model model, MemberVO memberVO ) {
		
		String session_member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		String session_member_name = (String)session.getAttribute("SESSION_MEMBER_NAME");
		
		if("".equals(session_member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", "로그인이 필요합니다.");
			return "redirect:/home";
		}
		
		int result = 0;
		result = withdrawService.deleteMember(memberVO);
		
		if(result > 0){
			redirectAttributes.addFlashAttribute("member_name", session_member_name);
			
			session.invalidate();
			
			return "redirect:/member/withdrawComplete";
			
		}else{
			redirectAttributes.addFlashAttribute("memberVO", memberVO);
			redirectAttributes.addFlashAttribute("alertMessage", "비밀번호가 일치하지 않습니다.");
			
			return "redirect:/member/withdraw";
		}
	}
	
	// 회원탈퇴 완료
	@RequestMapping(value = "/member/withdrawComplete", method = RequestMethod.GET)
	public String withdrawComplete(Model model ) {

		return "/app/member/withdrawComplete";
	}
	
}
