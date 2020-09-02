package com.astek.myquotes.entitites;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.astek.myquotes.utility.Utils;

@Entity
@Table(name = "QUOTE")

@SequenceGenerator(name = "seqQuote", sequenceName = "SEQ_QUOTE", initialValue = 1, allocationSize = 1) 
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqQuote")
	@Column(name = "QUOTE_ID")
	private Integer id;

	@Column(name = "QUOTE_TITRE", length = 50)
	private String titre;
	
	@Column(name = "QUOTE_VALUE", length = 250)
	private String value;
	
	@Column(name = "QUOTE_CONTEXTE", length = 500)
	private String contexte;
	
	@Column(name = "QUOTE_LIEU", length = 50)
	private String lieu;
	
	@Column(name = "QUOTE_PRIVEE")
	private Boolean privee;
	
	@Temporal(TemporalType.DATE)
	@Column(name="QUOTE_DATE_EVENEMENT")
	private Date dtEvenement;
	
	@Temporal(TemporalType.DATE)
	@Column(name="QUOTE_DATE_CREATION")
	private Date dtCreation;
	
//	@ManyToOne
//	@JoinColumn(name = "id", foreignKey = @ForeignKey(name = "AUTEUR_ID_FK"))
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "auteur_id", nullable = false)
	private Auteur auteur;	
	
	
	
public Quote(String titre, String value, String contexte, String lieu, Boolean privee, String dtEvenement,
		String dtCreation, Auteur auteur) throws ParseException {
		super();
		this.titre = titre;
		this.value = value;
		this.contexte = contexte;
		this.lieu = lieu;
		this.privee = privee;
		this.dtEvenement = Utils.sdf.parse(dtEvenement);
		this.dtCreation = Utils.sdf.parse(dtCreation);
		this.auteur = auteur;
	}

//	@ManyToOne
//	@JoinColumn(name = "AUTEUR_ID")
//	private Login login;
//	
//	@ManyToOne
//	@JoinColumn(name = "UTILISATEUR_ID", foreignKey = @ForeignKey(name = "UTILISATEUR_ID_FK"))
//	private Utilisateur createur;
	
	@Version
	private int version;
	
	public Quote() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getContexte() {
		return contexte;
	}

	public void setContexte(String contexte) {
		this.contexte = contexte;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Boolean getPrivee() {
		return privee;
	}

	public void setPrivee(Boolean privee) {
		this.privee = privee;
	}

	public Date getDtEvenement() {
		return dtEvenement;
	}

	public void setDtEvenement(Date dtEvenement) {
		this.dtEvenement = dtEvenement;
	}

	public Date getDtCreation() {
		return dtCreation;
	}

	public void setDtCreation(Date dtCreation) {
		this.dtCreation = dtCreation;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
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
		Quote other = (Quote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Quote [titre=" + titre + ", value=" + value + ", contexte=" + contexte + ", lieu=" + lieu + ", privee="
				+ privee + ", dtEvenement=" + dtEvenement + ", dtCreation=" + dtCreation + ", auteur=" + auteur + "]";
	}
	
	

}
