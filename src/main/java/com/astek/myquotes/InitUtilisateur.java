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
		tFou = tagRepository.save(tFou);
		tFou.setDescription("les dingeuries quoi !");
		tFou = tagRepository.save(tFou);

		createQuotes(aHugo);

		// ---
		retrieveAuteursTests();
		// ---
		retrieveTagFromUtilisateur(uToto);
		// ---
		retrieveQuotesFromAuteur(aHugo);

	}
	
	private static final String[] contexts = {
			"il faisait beau",
			"il faisait triste",
			"c'etait où ?",
			"qu'est ce qu'on a rigole !",
			"c'est pas important :)"
	};
	private String randomContext(int i) {
		return contexts[i%5];
	}
	
	private static final String[] quotes = {
			"Le cœur a ses raisons que la raison ne connaît pas.",
			"Le souvenir, c'est la présence invisible",
			"On va l'accrocher à un fil invisible. Un fil invisible, c'est comme un homme invisible, mais en forme de fil.",
			"Un athéiste est un homme qui n'a pas de soutien invisible.",
			"Pour se rendre invisible n'importe quel homme n'a pas de moyen plus sûr que de devenir pauvre."
	};
	private String randomQuote(int i) {
		return quotes[i%5];
	}

	private void createQuotes(Auteur auteur) throws ParseException {

		Boolean b;
		for (int i = 0; i < 250; i++) {

			if (i % 3 == 0) {
				b = Boolean.FALSE;
			} else {
				b = Boolean.TRUE;
			}
			Quote q1 = new Quote("De  #" + i, randomQuote(i), randomContext(i), "a pairs", b,
					"20/02/1984", "11/12/2019", auteur);

			q1 = quoteRepository.save(q1);

		}

	}

	private void retrieveQuotesFromAuteur(Auteur auteur) {
		List<Quote> lQuote = quoteRepository.findAll();// quoteRepository.findByAuteur(auteur);
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
			if (a.getNom() != null && a.getPrenom() != null) {
				System.out.println("Auteur " + (i++) + " n'est pas un utilisateur : " + a.toString());
			} else if (a.getUtilisateur() != null) {
				System.out.println("Auteur " + (i++) + " est un utilisateur : " + a.toString());
			} else {
				System.out.println("Probleme avec l'Auteur " + (i++) + " tout est null..");
			}

		}
	}

}
