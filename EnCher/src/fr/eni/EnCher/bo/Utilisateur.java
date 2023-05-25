package fr.eni.EnCher.bo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Utilisateur {
	private int idUtilisateur;
	private String pseudo;
	private String prenom;
	private String nom;
	private long numeroTel;
	private String email;
	private LocalDate dateNaissance;
	private String rue;
	private String ville;
	private String codePostal;
	private String motDePasse;
	private int credit;
	private LocalDateTime dateCreation;
	private Photo photoProfil;
	private boolean admin;
	
	private List<Article> listeArticle;
	
	

	public Utilisateur(String pseudo, String prenom, String nom, long numeroTel, String email, LocalDate dateNaissance,
			String rue, String ville, String codePostal, String motDePasse, int credit, Photo photoProfil) {
		super();
		this.pseudo = pseudo;
		this.prenom = prenom;
		this.nom = nom;
		this.numeroTel = numeroTel;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.photoProfil = photoProfil;
	}

	public Utilisateur(int idUtilisateur, String pseudo, String prenom, String nom, long numeroTel, String email,
			LocalDate dateNaissance, String rue, String ville, String codePostal, int credit,
			LocalDateTime dateCreation, Photo photoProfil, boolean admin) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.pseudo = pseudo;
		this.prenom = prenom;
		this.nom = nom;
		this.numeroTel = numeroTel;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.credit = credit;
		this.dateCreation = dateCreation;
		this.photoProfil = photoProfil;
		this.admin = admin;
	}

	public Utilisateur(int idUtilisateur, String pseudo, Photo photoProfil) {
		this.idUtilisateur = idUtilisateur;
		this.pseudo = pseudo;
		this.photoProfil = photoProfil;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public long getNumeroTel() {
		return numeroTel;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public String getRue() {
		return rue;
	}

	public String getVille() {
		return ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public Photo getPhotoProfil() {
		return photoProfil;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public List<Article> getListeArticle() {
		return listeArticle;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNumeroTel(long numeroTel) {
		this.numeroTel = numeroTel;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setPhotoProfil(Photo photoProfil) {
		this.photoProfil = photoProfil;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void setListeArticle(List<Article> listeArticle) {
		this.listeArticle = listeArticle;
	}
	
	
}
