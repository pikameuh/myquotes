package com.astek.myquotes.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.astek.myquotes.entitites.Utilisateur;
import com.astek.myquotes.repositories.UtilisateurRepository;

/**
 * Gestion security : Utilisateur existe ?
 * @author Astek
 *
 */
@Service
public class UtilisateurUserDetailsService implements UserDetailsService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utilisateur> opt = utilisateurRepository.findByLogin(username);
		if (opt.isPresent()) {
			return new UtilisateurUserDetails(opt.get());
		}
		throw new UsernameNotFoundException("utilisateur inconnu");
	}

}
