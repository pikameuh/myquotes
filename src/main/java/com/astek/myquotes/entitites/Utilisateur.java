package com.astek.myquotes.entitites;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.astek.myquotes.security.Role;
import com.astek.myquotes.utility.Utils;

@Entity
@Table(name = "UTILISATEUR")

@SequenceGenerator(name="seqUtilisateur", sequenceName = "SEQ_UTILISATEUR", initialValue = 1, allocationSize = 1) // nom obj java, nom seq ds DataBase, start, pas (icremente e 1)
@NamedQueries({
	@NamedQuery(name = "Utilisateur.findAll", query = "select u from Utilisateur u"),
	@NamedQuery(name = "Utilisateur.findByDtNaiss", query = "select u from Utilisateur u where u.dtNaiss=:dtNaiss")
})

public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seqUtilisateur")
	@Column(name="UTILISATEUR_ID")
	private Integer id;
	
	@Column(name="UTILISATEUR_NOM_AFFICHAGE", length = 150, nullable = false)
	private String nickname;
	
	@Column(name="UTILISATEUR_PRENOM", length = 150, nullable = false)
	private String prenom;
	
	@Column(name="UTILISATEURE_NOM", length = 150, nullable = false)
	private String nom;
	
	@Column(name="UTILISATEUR_LOGIN", length = 150, nullable = false)
	private String login;
	
	@Column(name="UTILISATEUR_PASSWORD", nullable = false)
	private String password;
	
	@Column(name="UTILISATEUR_EMAIL", length = 150, nullable = false)
	private String email;
	
	@Column(name="UTILISATEUR_ACTIVEE", nullable = false)
	private Boolean enable;
	
	@Temporal(TemporalType.DATE)
	@Column(name="UTILISATEUR_DATE_NAISSANCE")
	private Date dtNaiss;
	
	@Temporal(TemporalType.DATE)
	@Column(name="UTILISATEUR_DATE_CREATION_COMPTE")
	private Date dtCreation;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	/*
	 * faire en sorte de pouvoir a voir automatiquement ??
	 * permet de gerer lintegrite des données, modifiation ok que si version est le meme
	 */
	@Version
	private int version;	

	public Utilisateur() {

	}

	public Utilisateur(String nickname, String prenom, String nom, String login, String password, String email, Boolean enable, String dtNaiss, String dateCrea,
			Role role) throws ParseException {
		super();
		this.nickname = nickname;
		this.prenom = prenom;
		this.nom = nom;
		this.login = login;
		this.password = password;
		this.email = email;
		this.enable = enable;
		this.dtNaiss = Utils.sdf.parse(dtNaiss);
		this.dtCreation = Utils.sdf.parse(dateCrea);
		this.role = role;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDtNaiss() {
		return dtNaiss;
	}

	public void setDtNaiss(Date dtNaiss) {
		this.dtNaiss = dtNaiss;
	}

	public Date getDtCreation() {
		return dtCreation;
	}

	public void setDtCreation(Date dtCreation) {
		this.dtCreation = dtCreation;
	}		

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isEmpty() {
		if (getLogin() == null || getLogin().isEmpty()) {
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "Utilisateur [prenom=" + prenom + ", nom=" + nom + ", login=" + login + ", password=" + password
				+ ", email=" + email + ", enable=" + enable + ", dtNaiss=" + dtNaiss + ", dtCreation=" + dtCreation
				+ ", role=" + role + "]";
	}
	
	
}
