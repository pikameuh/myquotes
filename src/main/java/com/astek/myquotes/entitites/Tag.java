package com.astek.myquotes.entitites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "TAG")
@SequenceGenerator(name = "seqTag", sequenceName = "SEQ_TAG", initialValue = 1, allocationSize = 1)
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTag")
	@Column(name = "TAG_ID")
	private Integer id;

	@Column(name = "TAG_NOM", length = 50)
	private String nom;

	/* Si l'auteur n'est pas un utilisateur de l'application */
	@Column(name = "TAG_DESCRIPTION", length = 150)
	private String description;

	@OneToOne
	@JoinColumn(name = "TAG_CREATEUR", foreignKey = @ForeignKey(name = "UTILISATEURE_ID_FK"))
	private Utilisateur createur;

	@Version
	private int version;

	public Tag() {
	}

	public Tag(String nom, String description, Utilisateur createur) {
		super();
		this.nom = nom;
		this.description = description;
		this.createur = createur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Utilisateur getCreateur() {
		return createur;
	}

	public void setCreateur(Utilisateur createur) {
		this.createur = createur;
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
		Tag other = (Tag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", nom=" + nom + ", description=" + description + ", createur=" + createur
				+ ", version=" + version + "]";
	}
	
	

}
