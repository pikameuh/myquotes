package com.astek.myquotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.astek.myquotes.entitites.Quote;
import com.astek.myquotes.repositories.QuoteRepository;
import com.astek.myquotes.services.QuotesService;

@Controller
public class MainController {	
	
	@Autowired
	QuoteRepository quoteRepository;
	
	 @Autowired
	 private QuotesService quoteService;

	@GetMapping({ "", "/", "/index" })
	public String index(Model model, @Param("keyword") String keyword) {
		if(keyword != null) {
			List<Quote> quotes = quoteService.listAll(keyword);
			model.addAttribute("quotes", quotes);
			model.addAttribute("keyword", keyword);
			return "index";
		}else {
			model.addAttribute("quotes", quoteRepository.findByPrivee(Boolean.TRUE));
			return "index";
		}
		
		
	}

	@GetMapping("/page1")
	public String page() {
		return "page1";
	}
	
	@GetMapping("/search")
	public String search() {
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
