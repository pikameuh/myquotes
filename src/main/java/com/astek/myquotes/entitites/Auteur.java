package com.astek.myquotes.entitites;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.domain.Page;

/**
 * [Nom/Prenom] OU [UserLogin]
 * 
 * @author Astek
 *
 */

@Entity
@Table(name = "AUTEUR")

@SequenceGenerator(name = "seqAuteur", sequenceName = "SEQ_AUTEUR", initialValue = 1, allocationSize = 1) // nom obj java, nom
																									// seq ds DataBase,
																									// start, pas
																									// (icremente e 1)

public class Auteur {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAuteur")
	@Column(name = "AUTEUR_ID")
	private Integer id;
	
	@Column(name = "AUTEUR_NOM_ARTISTE", length = 150)
	private String nickname;

	/* Si l'auteur n'est pas un utilisateur de l'application */
	@Column(name = "AUTEUR_PRENOM", length = 150)
	private String prenom;
	
	@Column(name = "AUTEUR_NOM", length = 150)
	private String nom;

//	/* Si l'auteur est un utilisateur de l'application */
//	@Column(name="AUTEUR_LOGIN", length = 150)
//	private String login;

	@OneToOne
	@JoinColumn(name = "UTILISATEURE_ID", foreignKey = @ForeignKey(name = "UTILISATEURE_ID_FK"))
	private Utilisateur utilisateur;
	
//	@OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "auteur", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	private Set<Quote> quotes;

	public Set<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(Set<Quote> quotes) {
		this.quotes = quotes;
	}

	@Version
	private int version;

	public Auteur() {
	}

	public Auteur(String nickname, String prenom, String nom) {
		super();
		this.nickname = nickname;
		this.prenom = prenom;
		this.nom = nom;
	}
	
	public Auteur(String nickname, String prenom, String nom, Utilisateur utilisateur) {
		super();
		this.nickname = nickname;
		this.prenom = prenom;
		this.nom = nom;
		this.utilisateur = utilisateur;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

//	@Override
//	public String toString() {
//		if(getNom() != null && getPrenom() != null) {
//			return "Auteur [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", version=" + version + "]";
//		} else if (getUtilisateur() != null) {
//			return "Auteur [id=" + id + ", prenom=" + utilisateur.getPrenom() + ", nom=" + utilisateur.getNom() + ", login=" + utilisateur.getLogin() + ", version=" + version + "]";
//		}else {
//			return "Auteur [id=" + id + "null]";
//		}	
//	}	
	
	public boolean isUtilisateur() {
		if (getUtilisateur() != null) {
			return true;
		}
		
		return false;
	}
}
