package fr.eni.EnCher.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Utilisateur {
	private int idUtilisateur;
	private String pseudo;
	private String prenom;
	private String nom;
	private int numeroTel;
	private String email;
	private LocalDate dateNaissance;
	private String rue;
	private String ville;
	private int codePostal;
	private String motDePasse;
	private int credit;
	private LocalDateTime dateCreation;
	private boolean admin;
	
	private List<Article> listeArticle;
}
