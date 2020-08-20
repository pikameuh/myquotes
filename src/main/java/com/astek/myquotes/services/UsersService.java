package com.astek.myquotes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astek.myquotes.entitites.Utilisateur;
import com.astek.myquotes.repositories.UtilisateurRepository;


@Service
public class UsersService {
	@Autowired
	UtilisateurRepository usersRepository;

	// insert ou update
	public void save(Utilisateur users) {
		System.out.println("try to save Utilisateur : " + users.toString());
		usersRepository.save(users);
	}

	public void delete(Utilisateur users) {
		usersRepository.delete(users);
	}

	public void deleteById(String id) {
		usersRepository.deleteByLogin(id);
	}

	public List<Utilisateur> findAll() {
		return usersRepository.findAll();
	}

	public Utilisateur findById(String id) {
		Optional<Utilisateur> opt = usersRepository.findByLogin(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return new Utilisateur();

	}
	
	public Utilisateur findByEmail(String mail) {
		Optional<Utilisateur> opt = usersRepository.findByEmail(mail);
		if (opt.isPresent()) {
			return opt.get();
		}
		return new Utilisateur();

	}

}
