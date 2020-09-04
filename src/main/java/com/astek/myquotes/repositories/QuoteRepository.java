package com.astek.myquotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astek.myquotes.entitites.Quote;
import com.astek.myquotes.utility.SQL_CTE;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

//	List<Quote> findByTitre(String titre);
//	
//	void deleteByTitre(String titre);

	List<Quote> findByPrivee(Boolean privee);

//	List<Quote> findByAuteur(Auteur auteur);
//	
//	void deleteByAuteur(Auteur tauteuritre);
	@Query(SQL_CTE.selectQuoteLike + " %?1% AND (q.privee = 'false')")
	public List<Quote> searchInAllPublic(String keyword);

	@Query(SQL_CTE.selectQuoteLike + " %?1% ")
	public List<Quote> searchInAll(String keyword);

}
