package com.astek.myquotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astek.myquotes.entitites.Auteur;
import com.astek.myquotes.entitites.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

//	List<Quote> findByTitre(String titre);
//	
//	void deleteByTitre(String titre);
	
	List<Quote> findByPrivee(Boolean privee);
	
//	List<Quote> findByAuteur(Auteur auteur);
//	
//	void deleteByAuteur(Auteur tauteuritre);
}
