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
		tagDAO.modifier(tag);
	}
	
	public void ajouter(Tag tag) throws EncherException{
		tagDAO.ajouter(tag);
	}
	
	public void supprimer(Tag tag) throws EncherException{
		tagDAO.supprimer(tag);
	}
}
