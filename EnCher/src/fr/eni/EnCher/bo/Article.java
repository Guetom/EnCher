package fr.eni.EnCher.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Article {
	private int idArticle;
	private String nom;
	private String description;
	private int prix;
	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;
	private Utilisateur proprietaire;
	private Retrait retrait;
	private Categorie categorie;
	private List<Tag> listeTag;
	private String etat;
	private Photo photoPrincipal;
	
	private List<Photo> listeImage;
	
	public Article(int idArticle, String nom, String description, int prix, LocalDateTime dateDebut, LocalDateTime dateFin,
			Utilisateur proprietaire, Retrait retrait, Categorie categorie, String etat, Photo photoPrincipal) {
		super();
		this.idArticle = idArticle;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.proprietaire = proprietaire;
		this.retrait = retrait;
		this.categorie = categorie;
		this.etat = etat;
		this.photoPrincipal = photoPrincipal;
	}
	
	
	
	public Article(String nom, String description, int prix, LocalDateTime dateDebut, LocalDateTime dateFin,
			Utilisateur proprietaire, Retrait retrait, Categorie categorie, List<Tag> listeTag) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.proprietaire = proprietaire;
		this.retrait = retrait;
		this.categorie = categorie;
		this.listeTag = listeTag;
	}



	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

	public int getPrix() {
		return prix;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public LocalDateTime getDateFin() {
		return dateFin;
	}

	public Utilisateur getProprietaire() {
		return proprietaire;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public List<Tag> getListeTag() {
		return listeTag;
	}

	public String getEtat() {
		return etat;
	}

	public List<Photo> getListeImage() {
		return listeImage;
	}

	public Photo getPhotoPrincipal() {
		return photoPrincipal;
	}

	public void setListeImage(List<Photo> listeImage) {
		this.listeImage = listeImage;
	}
	
	
	
	

}
