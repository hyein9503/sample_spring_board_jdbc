package com.simpolor.app.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.member.service.MemberService;
import com.simpolor.app.member.vo.MemberVO;

@Controller
public class MemberController {
	
	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 회원정보 상세조회
	@RequestMapping(value = "/member/view", method = RequestMethod.GET)
	public String view( RedirectAttributes redirectAttributes, Model model, HttpSession session ) {
		
		String session_member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		
		if("".equals(session_member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", "로그인이 필요합니다.");
			return "redirect:/home";
		}
		
		MemberVO memberVO = memberService.selectMember(session_member_id);
		
		model.addAttribute("memberVO", memberVO);

		return "/app/member/view";
	}
	
	// 회원정보 수정 폼
	@RequestMapping(value = "/member/edit", method = RequestMethod.GET)
	public String edit( RedirectAttributes redirectAttributes, HttpSession session, Model model ) {
		
		String session_member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		
		if("".equals(session_member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", "로그인이 필요합니다.");
			return "redirect:/home";
		}
		
		MemberVO memberVO = memberService.selectMember(session_member_id);
		
		model.addAttribute("memberVO", memberVO);

		return "/app/member/edit";
	}
	
	// 회언정보 수정 처리
	@RequestMapping(value = "/member/edit", method = RequestMethod.POST)
	public String edit( RedirectAttributes redirectAttributes, HttpSession session, Model model, MemberVO memberVO ) {
		
		String session_member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		
		if("".equals(session_member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", "로그인이 필요합니다.");
			return "redirect:/board/list";
		}
		
		memberVO.setMember_id(session_member_id);
		memberVO.setMember_name(memberVO.getMember_name());
		memberVO.setEmail(memberVO.getEmail());
		
		int result = 0;
		result = memberService.updateMember(memberVO);
		
		if(result > 0){
			redirectAttributes.addFlashAttribute("alertMessage", "정보 변경이 완료되었습니다.");
			return "redirect:/member/view";
		}else{
			model.addAttribute("alertMessage", "수정하는 도중에 오류가 발생하였습니다.");
			return "/app/member/edit";
		}
	}
	
	// 회원 비밀번호 수정 폼
	@RequestMapping(value = "/member/password", method = RequestMethod.GET)
	public String password( RedirectAttributes redirectAttributes, HttpSession session, Model model ) {
		
		String session_member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		
		if("".equals(session_member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", "로그인이 필요합니다.");
			return "redirect:/home";
		}
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(session_member_id);
		
		model.addAttribute("memberVO", memberVO);

		return "/app/member/password";
	}
	
	// 회원 비밀번호 수정 처리
	@RequestMapping(value = "/member/password", method = RequestMethod.POST)
	public String password( RedirectAttributes redirectAttributes, Model model, MemberVO memberVO ) {
		
		String member_id = memberVO.getMember_id();
		String member_pw_org = memberVO.getMember_pw_org();
		String member_pw = memberVO.getMember_pw();
		String member_pw2 = memberVO.getMember_pw2();
		
		if(member_pw.equals(member_pw2)){
			int dupCheckResult = memberService.selectMemberDupCheck(member_id, member_pw_org);
			
			if(dupCheckResult > 0){
				int result = 0;
				result = memberService.updateMemberPassword(memberVO);
				
				if(result > 0){
					redirectAttributes.addFlashAttribute("alertMessage", "비밀번호 변경이 완료되었습니다.");
					
					return "redirect:/member/view";
					
				}else{
					model.addAttribute("memberVO", memberVO);
					model.addAttribute("alertMessage", "비밀번호를 변경하는 도중 문제가 발생하였습니다.");
					
					return "/app/member/password";
					
				}
			}else{
				model.addAttribute("memberVO", memberVO);
				model.addAttribute("alertMessage", "기존의 비밀번호가 정확하지 않습니다.");
				
				return "/app/member/password";
			}
			
		}else{
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("alertMessage", "변경할 비밀번호와 비밀번호 재확인과 일치하지 않습니다.");
			
			return "/app/member/password";
		}
	}
}
