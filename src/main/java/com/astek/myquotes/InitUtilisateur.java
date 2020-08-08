package com.astek.myquotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.astek.myquotes.security.Role;
import com.astek.myquotes.security.Utilisateur;
import com.astek.myquotes.security.UtilisateurRepository;

@Service
public class InitUtilisateur implements CommandLineRunner {

	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
//		Utilisateur u = new Utilisateur("toto", "toto", passwordEncoder.encode("toto"), Role.ROLE_USER);
//		utilisateurRepository.save(u);
//		
//		u = new Utilisateur("admin", "admin", passwordEncoder.encode("admin"), Role.ROLE_ADMIN);
//		utilisateurRepository.save(u);
		
		Utilisateur u = new Utilisateur("toto", "toto", "toto", passwordEncoder.encode("toto"), "25/07/19990", "08/08/2020", Role.ROLE_USER);
		utilisateurRepository.save(u);
		
		u = new Utilisateur("admin", "admin", "admin", passwordEncoder.encode("admin"), "25/07/19990", "08/08/2020", Role.ROLE_ADMIN);
		utilisateurRepository.save(u);
	}

}
