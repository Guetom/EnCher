package fr.eni.EnCher.bll;

import java.util.List;

import fr.eni.EnCher.bo.Enchere;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.dal.sqlServer.EnchereDAOSqlServer;
import fr.eni.EnCher.exception.EncherException;

public class EnchereManager {
	private static EnchereManager instance;
	private EnchereDAOSqlServer enchereDAO = new EnchereDAOSqlServer();
	
	public static EnchereManager getManager() {
		if (instance == null) {
			instance = new EnchereManager();			
		}
		return instance;		
	}
	
	public List<Enchere> selectionner(Lister choix) throws EncherException{
		return enchereDAO.selectionner(choix);
	}
	
	public void modfier(Enchere enchere) throws EncherException{
		enchereDAO.modifier(enchere);
	}
	
	public void ajouter(Enchere enchere) throws EncherException{
		enchereDAO.ajouter(enchere);
	}
	
	public void supprimer(Enchere enchere) throws EncherException{
		enchereDAO.supprimer(enchere);
	}

}
