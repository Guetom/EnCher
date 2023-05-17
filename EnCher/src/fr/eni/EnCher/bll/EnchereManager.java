package fr.eni.EnCher.bll;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.EnCher.bo.Enchere;
import fr.eni.EnCher.bo.Utilisateur;
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

		EncherException encherException = new EncherException();
		
		validerContenu(enchere, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		}
		else {
			enchereDAO.modifier(enchere);
		}
	}
	
	public void ajouter(Enchere enchere) throws EncherException{
		
		EncherException encherException = new EncherException();
		
		validerContenu(enchere, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		}
		else {
			enchereDAO.ajouter(enchere);
		}
	}
	
	public void supprimer(Enchere enchere) throws EncherException{
		enchereDAO.supprimer(enchere);
	}
	
	private void validerContenu(Enchere enchere, EncherException encherException) throws EncherException{
		
	    if (enchere.getDateHeureEnchere() == null) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_DATE_INVALIDE);
	    }
	    
	    if (enchere.getMontant() <= 0) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_MONTANT_INVALIDE);
	    }
	    
	    Utilisateur encherisseur = enchere.getEncherisseur();
	    if (encherisseur == null) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_ENCHERISSEUR_INVALIDE);
	    }
	    
	    if (enchere.getArticle() == null) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_ARTICLE_INVALIDE);
	    }
	    
	    if (enchere.getArticle().getDateFin().isBefore(LocalDateTime.now())) {
	        encherException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_ARTICLE_INDISPONIBLE);
	    }
    }
}
