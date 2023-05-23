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
		EncherException encherException = new EncherException();
		
		validerContenu(retrait, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		} else {
			retraitDAO.modifier(retrait);
		}

	}
	
	public void ajouter(Retrait retrait) throws EncherException{
		EncherException encherException = new EncherException();
		
//		validerContenu(retrait, encherException);
		
//		if(encherException.hasErreurs()) {
//			throw encherException;
//		} else {
			retraitDAO.ajouter(retrait);
//		}
	}
	
	public void supprimer(Retrait retrait) throws EncherException{
		EncherException encherException = new EncherException();
		
		validerContenu(retrait, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		} else {
			retraitDAO.supprimer(retrait);
		}
	}
	
	private void validerContenu(Retrait retrait, EncherException encherException) throws EncherException{
	    
	    if (retrait.getRue() == null || retrait.getRue().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_RUE_INVALIDE);
	    }
	    
	    if (retrait.getVille() == null || retrait.getVille().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_VILLE_INVALIDE);
	    }
	    
	    if (retrait.getCodePostal() == null || retrait.getCodePostal().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_CP_INVALIDE);
	    }
	}

}
