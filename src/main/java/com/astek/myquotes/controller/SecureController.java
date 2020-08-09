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
	
	@GetMapping("/info")
	public String info() {
		return "secure/info";
	}
	
	@GetMapping("/addquote")
	public String page2(Model model) {
		model.addAttribute("users", userService.findAll());
		return "secure/addquote";
	}
}
