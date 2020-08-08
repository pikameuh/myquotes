package com.astek.myquotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.astek.myquotes.services.UsersService;

@Controller
public class MainController {	

	@GetMapping({ "", "/", "/index" })
	public String index() {
		return "index";
	}

	@GetMapping("/page1")
	public String page() {
		return "page1";
	}
}
