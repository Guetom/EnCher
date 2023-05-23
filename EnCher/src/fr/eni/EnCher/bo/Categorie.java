package fr.eni.EnCher.bo;

public class Categorie {
	private int idCategorie;
	private String libelle;
	
	public Categorie(int idCategorie, String libelle) {
		super();
		this.idCategorie = idCategorie;
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public int getIdCategorie() {
		return idCategorie;
	}
	
	

}
