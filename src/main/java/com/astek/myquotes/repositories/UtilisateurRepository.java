package com.astek.myquotes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astek.myquotes.entitites.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	Optional<Utilisateur> findByLogin(String login);
	
	Optional<Utilisateur> findByEmail(String email);
	
	void deleteByLogin(String login);

	Optional<Utilisateur> findByNickname(String nickname);
}
