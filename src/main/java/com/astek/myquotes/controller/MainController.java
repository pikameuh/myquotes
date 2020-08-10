package com.astek.myquotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {	

	@GetMapping({ "", "/", "/index" , "/css/**", "/img/**" })
	public String index() {
		return "index";
	}

	@GetMapping("/page1")
	public String page() {
		return "page1";
	}
	
	@GetMapping({ "/search" })
	public String open() {
		return "open/searchQuote";
	}
	
	@GetMapping({ "/contact" })
	public String contact() {
		return "open/contact";
	}
	
	@GetMapping({ "/about" })
	public String about() {
		return "open/about";
	}

}
