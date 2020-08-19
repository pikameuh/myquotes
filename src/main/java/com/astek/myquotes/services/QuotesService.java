package com.astek.myquotes.services;

import java.util.List;
import java.util.StringTokenizer;

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
			String sqlReqAttr = "";
			
//			if (keyword.contains("+")) {
//				StringTokenizer st=new StringTokenizer(keyword,"+");
//			     while (st.hasMoreTokens()) {
//			    	 sqlReqAttr += st.nextToken() + " or like ";
//			     }
//			     sqlReqAttr = sqlReqAttr.substring(0, sqlReqAttr.length() - 2);
//			} else {
				sqlReqAttr = keyword;
//			}
			System.out.println("**************************** " + sqlReqAttr);
			return repo.searchInAllPublic(sqlReqAttr);
		}
		return repo.findAll();
	}
}
