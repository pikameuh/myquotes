package com.astek.myquotes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astek.myquotes.entitites.Auteur;
import com.astek.myquotes.entitites.Utilisateur;

public interface AuteurRepository extends JpaRepository<Auteur, Integer> {

	Optional<Auteur> findByUtilisateur(Utilisateur user);
}
