package com.astek.myquotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astek.myquotes.entitites.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

//	List<Quote> findByTitre(String titre);
//	
//	void deleteByTitre(String titre);

	List<Quote> findByPrivee(Boolean privee);

//	List<Quote> findByAuteur(Auteur auteur);
//	
//	void deleteByAuteur(Auteur tauteuritre);
	@Query("SELECT q FROM Quote q WHERE CONCAT(q.value, q.contexte, q.lieu, q.titre, CONCAT(q.dtEvenement, ''), CONCAT(q.dtCreation, ''), q.auteur.prenom ) LIKE %?1% AND (q.privee = 'true')")
	public List<Quote> searchInAllPublic(String keyword);

	@Query("SELECT q FROM Quote q WHERE CONCAT(q.value, q.contexte, q.lieu, q.titre, CONCAT(q.dtEvenement, ''), CONCAT(q.dtCreation, ''), q.auteur.prenom ) LIKE %?1% ")
	public List<Quote> searchInAll(String keyword);

}
