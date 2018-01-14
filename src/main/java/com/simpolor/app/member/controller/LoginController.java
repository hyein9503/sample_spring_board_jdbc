package com.simpolor.app.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.member.service.LoginService;
import com.simpolor.app.member.vo.MemberVO;

@Controller
public class LoginController {
	
	private LoginService LoginService;
	
	public void setLoginService(LoginService LoginService) {
		this.LoginService = LoginService;
	}

	// 로그인 폼
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login(Model model, MemberVO memberVO) {

		return "/app/member/login";
	}
	
	// 로그인 처리
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String login(RedirectAttributes redirectAttributes, HttpSession session, Model model, MemberVO memberVO ) {
		
		String member_id = memberVO.getMember_id();
		String member_pw = memberVO.getMember_pw();
		
		memberVO = LoginService.selectMemberLogin(member_id, member_pw);
		
		if(memberVO != null){
			session.setAttribute("SESSION_MEMBER_ID", memberVO.getMember_id());
			session.setAttribute("SESSION_MEMBER_NAME", memberVO.getMember_name());
			session.setAttribute("SESSION_MEMBER_LEVEL", memberVO.getLevel());
			
			return "redirect:/home";
			
		}else{
			model.addAttribute("alertMessage", "정보가 일치하지 않습니다.");
			return "/app/member/login";
		}
	}
	
	// 로그아웃 처리
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logoutOk(HttpSession session, Model model) {
		
		session.invalidate();

		return "redirect:/home";
	}
}
