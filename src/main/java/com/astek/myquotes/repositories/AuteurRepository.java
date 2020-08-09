package com.astek.myquotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astek.myquotes.entitites.Auteur;

public interface AuteurRepository extends JpaRepository<Auteur, Integer> {

}
