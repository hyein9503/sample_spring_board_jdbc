package com.simpolor.app.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.board.service.BoardService;
import com.simpolor.app.board.vo.BoardVO;

@Controller
public class BoardController {
	
	static Logger logger = Logger.getLogger(BoardController.class);
	
	private BoardService boardService;
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 게시판 목록
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list( Model model ) {
		
		BoardVO boardVO = new BoardVO();
		
		List list = boardService.selectBoardList(boardVO);
		
		model.addAttribute("boardList", list);

		return "/app/board/list";
	}
	
	// 게시판 상세조회
	@RequestMapping(value = "/board/view/{seq}", method = RequestMethod.GET)
	public String view( Model model, @PathVariable("seq") int seq) {
		
		BoardVO boardVO  = null; 
		boardVO = boardService.selectBoard(seq);
		
		model.addAttribute("boardVO", boardVO);

		return "/app/board/view";
	}
	
	// 게시판 글등록 폼
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write( RedirectAttributes redirectAttributes, HttpSession session, BoardVO boardVO ) {
		
		String member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		
		if(member_id == null){
			redirectAttributes.addFlashAttribute("alertMessage", "로그인이 필요합니다.");
			return "redirect:/board/list";
		}
		return "/app/board/write";
	}
	
	// 게시판 글등록 처리
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String write(RedirectAttributes redirectAttributes, HttpSession session, Model model, BoardVO boardVO ) {
		
		String member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		String member_name = (String)session.getAttribute("SESSION_MEMBER_NAME");
		
		if("".equals(member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", "로그인이 필요합니다.");
			return "redirect:/board/list";
		}
		
		//boardVO.setContents(StringUtil.replaceHtmlY(boardVO.getContents()));
		boardVO.setReg_id(member_id);
		boardVO.setReg_name(member_name);
		
		int result = 0;
		result = boardService.insertBoard(boardVO);
		
		if(result > 0){
			return "redirect:/board/list";
		}else{
			model.addAttribute("alertMessage", "등록하는 도중에 오류가 발생하였습니다.");
			return "/app/board/write";
		}
	}
	
	// 게시판 글수정 폼
	@RequestMapping(value = "/board/edit/{seq}", method = RequestMethod.GET)
	public String edit( RedirectAttributes redirectAttributes, HttpSession session, Model model, @PathVariable("seq") int seq ) {
		
		String member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		
		if(member_id == null){
			redirectAttributes.addFlashAttribute("alertMessage", "로그인이 필요합니다.");
			return "redirect:/board/list";
		}
		
		BoardVO boardVO = boardService.selectBoard(seq);
		//boardVO.setContents(StringUtil.replaceHtmlN(boardVO.getContents()));
		
		model.addAttribute("boardVO", boardVO);

		return "/app/board/edit";
	}
	
	// 게시판 글수정 처리
	@RequestMapping(value = "/board/edit", method = RequestMethod.POST)
	public String edit(RedirectAttributes redirectAttributes, HttpSession session, Model model, BoardVO boardVO) {
		
		String member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		String member_name = (String)session.getAttribute("SESSION_MEMBER_NAME");
		
		String reg_id = boardVO.getReg_id();
		int seq = boardVO.getSeq();
		
		if("".equals(member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", "로그인이 필요합니다.");
			return "redirect:/board/list";
		}
		
		if(!member_id.equals(reg_id)){
			redirectAttributes.addFlashAttribute("alertMessage", "작성자가 아닙니다.");
			return "redirect:/board/edit/"+seq;
		}
		
		boardVO.setMod_id(member_id);
		boardVO.setMod_name(member_name);
		
		int result = 0;
		result = boardService.updateBoard(boardVO);
		
		if(result > 0){
			return "redirect:/board/list";
		}else{
			model.addAttribute("alertMessage", "수정하는 도중에 오류가 발생하였습니다.");
			return "/app/board/edit";
		}

	}
	
	// 게시판 글삭제 처리
	@RequestMapping(value = "/board/delete", method = RequestMethod.POST)
	public String delete(RedirectAttributes redirectAttributes, HttpSession session, Model model, BoardVO boardVO) {
		
		String member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		String reg_id = boardVO.getReg_id();
		int seq = boardVO.getSeq();
		
		if("".equals(member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", "로그인이 필요합니다.");
			return "redirect:/board/list";
		}
		
		int result = 0;
		
		if(!member_id.equals(reg_id)){
			redirectAttributes.addFlashAttribute("alertMessage", "작성자가 아닙니다.");
			return "redirect:/board/view/"+seq;
		}
		
		result = boardService.deleteBoard(seq);
		
		if(result > 0){
			redirectAttributes.addFlashAttribute("alertMessage", "삭제가 완료되었습니다.");
			return "redirect:/board/list";
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", "삭제하는 도중에 오류가 발생하였습니다.");
			return "redirect:/board/view/"+seq;
		}
	}
}
