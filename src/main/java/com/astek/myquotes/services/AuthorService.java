package com.astek.myquotes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astek.myquotes.entitites.Auteur;
import com.astek.myquotes.entitites.Utilisateur;
import com.astek.myquotes.repositories.AuteurRepository;

@Service
public class AuthorService {
	@Autowired
	AuteurRepository authorRepository;
	
	public Auteur findByUtilisateur(Utilisateur user) {
		Optional<Auteur> opt = authorRepository.findByUtilisateur(user);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;

	}

	public List<Auteur> findAll() {
		// TODO Auto-generated method stub
		return authorRepository.findAll();
	}	

}
