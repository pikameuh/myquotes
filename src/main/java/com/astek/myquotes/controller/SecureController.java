package com.astek.myquotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.astek.myquotes.services.UsersService;

@RequestMapping("/secure")
@Controller
public class SecureController {

	@Autowired
	private UsersService userService;
	
	@GetMapping("/page1")
	public String page1() {
		return "secure/page1";
	}
	
	@GetMapping("/page2")
	public String page2(Model model) {
		model.addAttribute("users", userService.findAll());
		return "secure/page2";
	}
}
