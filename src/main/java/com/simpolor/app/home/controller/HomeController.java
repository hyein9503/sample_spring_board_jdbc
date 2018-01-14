package com.simpolor.app.home.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String root(HttpSession session, Model model) {
		return "/app/home/home";
		
	}
	
	@RequestMapping("/home")
	public String home(HttpSession session, Model model) {
		return "/app/home/home";
		
	}

}
