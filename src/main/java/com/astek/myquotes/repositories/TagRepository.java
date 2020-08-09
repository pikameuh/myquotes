package com.astek.myquotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astek.myquotes.entitites.Tag;
import com.astek.myquotes.entitites.Utilisateur;

public interface TagRepository extends JpaRepository<Tag, Integer> {
	List<Tag> findByNom(String nom);
	
	void deleteByNom(String nom);
	
	List<Tag> findByCreateur(Utilisateur createur);
	
	void deleteByCreateur(Utilisateur createur);
}
