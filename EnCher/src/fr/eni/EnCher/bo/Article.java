package fr.eni.EnCher.bo;

import java.time.LocalDate;
import java.util.List;

public class Article {
	private int idArticle;
	private String nom;
	private String description;
	private int prix;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Utilisateur proprietaire;
	private Retrait retrait;
	private List<Tag> listeTag;
	
	private List<Photo> listeImage;

}
