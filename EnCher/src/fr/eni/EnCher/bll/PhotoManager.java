package fr.eni.EnCher.bll;

import java.util.List;

import fr.eni.EnCher.bo.Photo;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.dal.sqlServer.PhotoDAOSqlServer;
import fr.eni.EnCher.exception.EncherException;

public class PhotoManager {
	private static PhotoManager instance;
	private PhotoDAOSqlServer photoDAO = new PhotoDAOSqlServer();
	
	public static PhotoManager getManager() {
		if (instance == null) {
			instance = new PhotoManager();			
		}
		return instance;		
	}
	
	public List<Photo> selectionner(Lister choix) throws EncherException{
		return photoDAO.selectionner(choix);
	}
	
	public List<Photo> selectionner(int idArticle) throws EncherException{
		return photoDAO.selectionner(idArticle);
	}
	
	public void modfier(Photo photo) throws EncherException{
		photoDAO.modifier(photo);
	}
	
	public void ajouter(Photo photo) throws EncherException{
		photoDAO.ajouter(photo);
	}
	
	public void supprimer(Photo photo) throws EncherException{
		photoDAO.supprimer(photo);
	}

}
