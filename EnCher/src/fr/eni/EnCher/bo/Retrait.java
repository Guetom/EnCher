package fr.eni.EnCher.bo;

public class Retrait {
	private int idRetrait;
	private String rue;
	private String ville;
	private String codePostal;

	public Retrait(int idRetrait, String rue, String ville, String codePostal) {
		super();
		this.idRetrait = idRetrait;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}
		
	public Retrait(String rue, String ville, String codePostal) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}



	public int getIdRetrait() {
		return idRetrait;
	}

	public void setIdRetrait(int idRetrait) {
		this.idRetrait = idRetrait;
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
	

}
