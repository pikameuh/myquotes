package com.astek.myquotes.dao;

import java.util.List;

import com.astek.myquotes.entitites.Quote;

public interface DaoQuote extends DaoGeneric<Quote, Integer>{

	Quote findRandomQuote(Boolean onlyPublicQuotes);
	
	List<Quote> searchByKeyword(String keyword);

	long numberOfPublicQuote();
}
