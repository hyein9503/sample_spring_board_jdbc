package com.simpolor.app.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.member.service.JoinService;
import com.simpolor.app.member.vo.MemberVO;

@Controller
public class JoinController {
	
	private JoinService joinService;
	
	public void setJoinService(JoinService joinService) {
		this.joinService = joinService;
	}
	
	// 회원가입 폼
	@RequestMapping(value = "/member/join", method = RequestMethod.GET)
	public String join(Model model, MemberVO memberVO) {
		
		return "/app/member/join";
		
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
	public String join(RedirectAttributes redirectAttributes, Model model, MemberVO memberVO) {
		
		String member_id = memberVO.getMember_id();
		String member_pw = memberVO.getMember_pw();
		String member_pw2 = memberVO.getMember_pw2();
		String email = memberVO.getEmail();

		if(member_pw.equals(member_pw2)){
			int idDupCheckResult = joinService.selectMemberIdDupCheck(member_id);
			
			if(idDupCheckResult == 0){
				int emailDupCheckResult = joinService.selectMemberEmailDupCheck(email);
				
				if(emailDupCheckResult == 0){
					int result = 0;
					result = joinService.insertJoinMember(memberVO);
					
					if(result > 0){
						redirectAttributes.addFlashAttribute("member_name", memberVO.getMember_name());
						
						return "redirect:/member/joinComplete";
						
					}else{
						model.addAttribute("memberVO", memberVO);
						model.addAttribute("alertMessage", "회원을 등록하는 도중 오류가 발생하였습니다.");
						
						return "/app/member/join";
					}
				}else{
					model.addAttribute("memberVO", memberVO);
					model.addAttribute("alertMessage", "중복된 이메일이 있습니다.");
					
					return "/app/member/join";
				}
			}else{
				model.addAttribute("memberVO", memberVO);
				model.addAttribute("alertMessage", "등록된 아이디가 있습니다.");
				
				return "/app/member/join";
			}
		}else{
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("alertMessage", "비밀번호가 비밀번호 재확인과 일치하지 않습니다.");
			
			return "/app/member/join";
		}
	}
	
	// 회원가입 완료
	@RequestMapping(value = "/member/joinComplete", method = RequestMethod.GET)
	public String joinComplete(Model model, MemberVO memberVO) {
		
		return "/app/member/joinComplete";
	}
}
