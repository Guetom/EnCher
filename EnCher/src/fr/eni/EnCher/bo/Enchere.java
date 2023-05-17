package fr.eni.EnCher.bo;

import java.time.LocalDateTime;

public class Enchere {
	private int idEnchere;
	private LocalDateTime dateHeureEnchere;
	private int montant;
	private Utilisateur encherisseur;
	private Article article;
	
	public Enchere(int idEnchere, LocalDateTime dateHeureEnchere, int montant, Utilisateur encheriseur,
			Article article) {
		super();
		this.idEnchere = idEnchere;
		this.dateHeureEnchere = dateHeureEnchere;
		this.montant = montant;
		this.encherisseur = encheriseur;
		this.article = article;
	}

	public int getIdEnchere() {
		return idEnchere;
	}

	public void setIdEnchere(int idEnchere) {
		this.idEnchere = idEnchere;
	}

	public LocalDateTime getDateHeureEnchere() {
		return dateHeureEnchere;
	}

	public int getMontant() {
		return montant;
	}

	public Utilisateur getEncherisseur() {
		return encherisseur;
	}

	public Article getArticle() {
		return article;
	}
	
	
	
}
