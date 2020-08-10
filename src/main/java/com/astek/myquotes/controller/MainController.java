package com.astek.myquotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.astek.myquotes.repositories.QuoteRepository;

@Controller
public class MainController {	
	
	@Autowired
	QuoteRepository quoteRepository;

	@GetMapping({ "", "/", "/index" , "/css/**", "/img/**" })
	public String index(Model model) {
		model.addAttribute("quotes", quoteRepository.findByPrivee(Boolean.TRUE));
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
