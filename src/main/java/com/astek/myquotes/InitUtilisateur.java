package com.astek.myquotes;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.astek.myquotes.entitites.Auteur;
import com.astek.myquotes.entitites.Quote;
import com.astek.myquotes.entitites.Tag;
import com.astek.myquotes.entitites.Utilisateur;
import com.astek.myquotes.repositories.AuteurRepository;
import com.astek.myquotes.repositories.QuoteRepository;
import com.astek.myquotes.repositories.TagRepository;
import com.astek.myquotes.repositories.UtilisateurRepository;
import com.astek.myquotes.security.Role;

@Service
public class InitUtilisateur implements CommandLineRunner {

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	AuteurRepository auteurRepository;
	
	@Autowired
	TagRepository tagRepository;
	
	@Autowired
	QuoteRepository quoteRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
//		Utilisateur u = new Utilisateur("toto", "toto", passwordEncoder.encode("toto"), Role.ROLE_USER);
//		utilisateurRepository.save(u);
//		
//		u = new Utilisateur("admin", "admin", passwordEncoder.encode("admin"), Role.ROLE_ADMIN);
//		utilisateurRepository.save(u);

		Utilisateur uToto = new Utilisateur("toto", "toto", "toto", passwordEncoder.encode("toto"), "25/07/19990",
				"08/08/2020", Role.ROLE_USER);
		uToto = utilisateurRepository.save(uToto);

		Utilisateur uAdmin = new Utilisateur("admin", "admin", "admin", passwordEncoder.encode("admin"), "25/07/19990",
				"08/08/2020", Role.ROLE_ADMIN);
		uAdmin = utilisateurRepository.save(uAdmin);

		Auteur aToto = new Auteur(uToto);
		aToto = auteurRepository.save(aToto);

		Auteur aHugo = new Auteur("victor", "hugo");
		aHugo = auteurRepository.save(aHugo);
		
		Auteur aXXX = new Auteur("xxx", "xxxx");
		aXXX = auteurRepository.save(aXXX);
		
		Tag tDrole = new Tag("drole", "quand c'est rigolo", uToto);
		Tag tFou = new Tag("fou", "quand c'est la folie", uToto);
		tDrole = tagRepository.save(tDrole);
		tFou =  tagRepository.save(tFou);
		tFou.setDescription("les dingeuries quoi !");
		tFou =  tagRepository.save(tFou);
		
		Quote q1 = new Quote("titre", "lalala", "il faisait beau", "a pairs", Boolean.TRUE, "20/02/1884", "11/12/2019", aHugo);
		q1 = quoteRepository.save(q1);
		
		createQuotes(aHugo);
				
		
		// ---
		retrieveAuteursTests();
		// ---
		retrieveTagFromUtilisateur(uToto);
		// ---
		retrieveQuotesFromAuteur(aHugo);
		
	}
	
	private void createQuotes(Auteur auteur) throws ParseException {
		Quote q1 = new Quote("De " + auteur.getPrenom(), "lalala", "il faisait beau", "a pairs", Boolean.TRUE, "20/02/1884", "11/12/2019", auteur);

		q1 = quoteRepository.save(q1);
		
		
		
		Quote q2 = new Quote("De " + auteur.getPrenom(), "lalala", "il faisait beau", "a pairs", Boolean.TRUE, "20/02/1884", "11/12/2019", auteur);
		q2 = quoteRepository.save(q2);
		
//
//		Quote q3 = new Quote("De " + auteur.getPrenom(), "lalala", "il faisait beau", "a pairs", Boolean.TRUE, "20/02/1884", "11/12/2019", auteur);
//		q3 = quoteRepository.save(q3);
//
//		
//		Quote q4 = new Quote("De " + auteur.getPrenom(), "lalala", "il faisait beau", "a pairs", Boolean.TRUE, "20/02/1884", "11/12/2019", auteur);
//		q4 = quoteRepository.save(q4);

	}

	private void retrieveQuotesFromAuteur(Auteur auteur) {
		List<Quote> lQuote = quoteRepository.findAll();//quoteRepository.findByAuteur(auteur);
		System.out.println("*******************************************");
		System.out.println("        Quotes de " + auteur.toString());
		System.out.println("*******************************************");
		for (Quote q : lQuote) {
			System.out.println(q.toString());			
		}
		
	}

	private void retrieveTagFromUtilisateur(Utilisateur uToto) {
		List<Tag> lTagToto = tagRepository.findByCreateur(uToto);
		System.out.println("*******************************************");
		System.out.println("        Tags de " + uToto.getPrenom());
		System.out.println("*******************************************");
		for (Tag t : lTagToto) {
			System.out.println(t.toString());			
		}
		
	}

	private void retrieveAuteursTests() {
		// Test retrieve nom/prenom de Auteur
		List<Auteur> auteursList = auteurRepository.findAll();
		int i = 0;
		for (Auteur a : auteursList) {
			//
			if(a.getNom() != null && a.getPrenom() != null) {
				System.out.println("Auteur " + (i++) + " n'est pas un utilisateur : " + a.toString());
			}else if (a.getUtilisateur() != null) {
				System.out.println("Auteur " + (i++) + " est un utilisateur : " + a.toString());
			} else {
				System.out.println("Probleme avec l'Auteur " + (i++) + " tout est null..");
			}
			
		}
	}

}
