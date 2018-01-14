package com.simpolor.app.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.member.service.SearchService;
import com.simpolor.app.member.vo.MemberVO;

@Controller
public class SearchController {
	
	private SearchService searchService;
	
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	// 회원정보 찾기 폼
	@RequestMapping(value = "/member/search", method = RequestMethod.GET)
	public String search(Model model, MemberVO memberVO ) {

		return "/app/member/search";
	}
	
	// 회원정보 찾기 처리
	@RequestMapping(value = "/member/search", method = RequestMethod.POST)
	public String search(RedirectAttributes redirectAttributes, Model model, MemberVO memberVO ) {

		String member_name = memberVO.getMember_name();
		String email = memberVO.getEmail();

		memberVO = searchService.selectMemberSearch(member_name, email);
		
		if(memberVO != null){
			redirectAttributes.addFlashAttribute("memberVO", memberVO);
			return "redirect:/member/change";	
			
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", "일치된 정보가 없습니다.");
		
			return "redirect:/member/search";	
		}
	}
	
	// 회원 비밀번호 수정 폼
	@RequestMapping(value = "/member/change", method = RequestMethod.GET)
	public String change( Model model, MemberVO memberVO ) {
		
		System.out.println("memberVO : "+memberVO.getMember_id());
		
		model.addAttribute("memberVO", memberVO);
		
		return "/app/member/change";
	}
	
	// 회원 비밀번호 수정 처리
	@RequestMapping(value = "/member/change", method = RequestMethod.POST)
	public String change( RedirectAttributes redirectAttributes, Model model, MemberVO memberVO ) {
		
		
		String member_pw = memberVO.getMember_pw();
		String member_pw2 = memberVO.getMember_pw2();
		
		if(member_pw.equals(member_pw2)){
			
			int result = 0;
			result = searchService.updateMemberChange(memberVO);
			
			if(result > 0){
				redirectAttributes.addFlashAttribute("member_name", memberVO.getMember_name());
				
				return "redirect:/member/changeComplete";
				
			}else{
				model.addAttribute("memberVO", memberVO);
				model.addAttribute("alertMessage", "변경하는 도중 문제가 발생하였습니다.");
				
				return "/app/member/change";
			}
			
		}else{
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("alertMessage", "비밀번호가 비밀번호 재확인과 일치하지 않습니다.");
			
			return "/app/member/change";
		}
	}
	
	// 회원 비밀번호 수정 완료
	@RequestMapping(value = "/member/changeComplete", method = RequestMethod.GET)
	public String changeComplete(Model model ) {

		return "/app/member/changeComplete";
	}
}
