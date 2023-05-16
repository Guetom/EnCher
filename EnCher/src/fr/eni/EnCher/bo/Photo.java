package fr.eni.EnCher.bo;

public class Photo {
	private int idPhoto;
	private String url;
	
	
	
	public Photo(int idPhoto, String url) {
		super();
		this.idPhoto = idPhoto;
		this.url = url;
	}
	public int getIdPhoto() {
		return idPhoto;
	}
	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}
	public String getUrl() {
		return url;
	}
	
	
	
}
