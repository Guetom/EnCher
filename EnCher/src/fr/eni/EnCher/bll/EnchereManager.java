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
	
	public Enchere selectionner(int idArticle) throws EncherException{
		return enchereDAO.selectionner(idArticle);
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
	    
//	    if (enchere.getArticle().getDateFin().isBefore(LocalDateTime.now())) {
//	        encherException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_ARTICLE_INDISPONIBLE);
//	    }
	    
	    // On sélectionne l'enchère la plus haute par rapport à l'id de l'article de l'enchère en cours
	    Enchere enchereLaPlusHaute = this.selectionner(enchere.getArticle().getIdArticle());
	    
	    // S'il n'y a pas d'autres enchères en cours
	    if (enchereLaPlusHaute == null) {
	    	// On regarde si le montant de l'enchère en cours est plus bas ou égal au prix de l'article
	    	if(enchere.getMontant() <= enchere.getArticle().getPrix()) {
	    		encherException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_PLUS_BASSE_INITIAL);
	    	}
	    } else if (enchere.getMontant() <= enchereLaPlusHaute.getMontant()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_PLUS_BASSE);
	    }
	    
	    // Si encherisseur = celui qui a créé l'article
	    if(enchere.getEncherisseur().getIdUtilisateur() == enchere.getArticle().getProprietaire().getIdUtilisateur()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_MEME_ENCHERISSEUR_PROPRIO);
	    }
	    
	 // Si encherisseur = celui qui a créé l'article
	    if(enchere.getEncherisseur().getCredit() < enchere.getMontant()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_PAS_ASSEZ_CREDIT);
	    }

    }
}
