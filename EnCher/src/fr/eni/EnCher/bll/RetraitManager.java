package fr.eni.EnCher.bll;

import java.util.List;

import fr.eni.EnCher.bo.Retrait;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.dal.sqlServer.RetraitDAOSqlServer;
import fr.eni.EnCher.exception.EncherException;

public class RetraitManager {
	private static RetraitManager instance;
	private RetraitDAOSqlServer retraitDAO = new RetraitDAOSqlServer();
	
	public static RetraitManager getManager() {
		if (instance == null) {
			instance = new RetraitManager();			
		}
		return instance;		
	}
	
	public List<Retrait> selectionner(Lister choix) throws EncherException{
		return retraitDAO.selectionner(choix);
	}
	
	public void modfier(Retrait retrait) throws EncherException{
		retraitDAO.modifier(retrait);
	}
	
	public void ajouter(Retrait retrait) throws EncherException{
		retraitDAO.ajouter(retrait);
	}
	
	public void supprimer(Retrait retrait) throws EncherException{
		retraitDAO.supprimer(retrait);
	}

}
