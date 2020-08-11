package com.astek.myquotes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astek.myquotes.entitites.Quote;
import com.astek.myquotes.repositories.QuoteRepository;

@Service
public class QuotesService {

	@Autowired
	private QuoteRepository repo;

	public List<Quote> listAll(String keyword) {
		if (keyword != null) {
			return repo.search(keyword);
		}
		return repo.findAll();
	}
}
