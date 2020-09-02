package com.astek.myquotes.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.astek.myquotes.entitites.Auteur;
import com.astek.myquotes.entitites.Quote;
import com.astek.myquotes.entitites.Utilisateur;
import com.astek.myquotes.services.AuthorService;
import com.astek.myquotes.services.QuotesService;
import com.astek.myquotes.services.UsersService;
import com.astek.myquotes.utility.Log;

@RequestMapping("/secure")
@Controller
public class SecureController {

	@Autowired
	private UsersService userService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private QuotesService quoteService;
	
	@GetMapping("/info")
	public String info() {
		return "secure/info";
	}
	
	@GetMapping("/addquote")
	public String page2(Model model) {
		model.addAttribute("quote", new Quote());
		model.addAttribute("authors", authorService.findAll());
		return "secure/addquote";
	}
	
	@GetMapping("/profile")
	public String profile(Model model) {
		
		Utilisateur user = userService.findById(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		model.addAttribute("user", user);
		
		Auteur auteur = authorService.findByUtilisateur(user);
	//	Log.debug(auteur.toString());
		model.addAttribute("author", auteur);
		
		
		return "secure/profile";
	}
	
	@PostMapping("/savequote")
	public String saveQuote(@Valid @ModelAttribute("quote") Quote quote, BindingResult br, Model model) {
		Log.debug("***************************************** SecureController.saveQuote() *****************************************");
		if (br.hasErrors()) {
			//return goEdit(user, model);
			Log.debug("SecureController.saveQuote() - br.hasErrors()");
		}
		
		quote.setDtCreation(new Date());
		Log.debug(">>> Quote : " + quote.toString());
		Log.debug(">>> amodel : "+ model.toString());
		
		quoteService.save(quote);
		
		return "redirect:/index?quotecreated=true";
	}
}
