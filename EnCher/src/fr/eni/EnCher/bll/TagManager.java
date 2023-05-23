package fr.eni.EnCher.bll;

import java.util.List;

import fr.eni.EnCher.bo.Tag;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.dal.sqlServer.TagDAOSqlServer;
import fr.eni.EnCher.exception.EncherException;

public class TagManager {
	private static TagManager instance;
	private TagDAOSqlServer tagDAO = new TagDAOSqlServer();
	
	public static TagManager getManager() {
		if (instance == null) {
			instance = new TagManager();			
		}
		return instance;		
	}
	
	public List<Tag> selectionner(Lister choix) throws EncherException{
		return tagDAO.selectionner(choix);
	}
	
	public void modfier(Tag tag) throws EncherException{
		EncherException encherException = new EncherException();
		
		validerContenu(tag, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		} else {
			tagDAO.modifier(tag);
		}
		
	}
	
	public void ajouter(Tag tag) throws EncherException{
		EncherException encherException = new EncherException();
		
		validerContenu(tag, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		} else {
			tagDAO.ajouter(tag);
		}
	}
	
	public void supprimer(Tag tag) throws EncherException{
		EncherException encherException = new EncherException();
		
		validerContenu(tag, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		} else {
			tagDAO.supprimer(tag);
		}
	}
	
	private void validerContenu(Tag tag, EncherException encherException) throws EncherException{
	    
	    if (tag.getLibelle() == null || tag.getLibelle().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_TAG_LIBELLE_INVALIDE);
	    }
	}
}
