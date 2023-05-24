package fr.eni.EnCher.bll;

import java.util.List;

import fr.eni.EnCher.bo.Utilisateur;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.dal.sqlServer.UtilisateurDAODqlServer;
import fr.eni.EnCher.exception.EncherException;

public class UtilisateurManager {
	private static UtilisateurManager instance;
	private UtilisateurDAODqlServer utilisateurDAO = new UtilisateurDAODqlServer();
	
	public static UtilisateurManager getManager() {
		if (instance == null) {
			instance = new UtilisateurManager();			
		}
		return instance;		
	}
	
	public List<Utilisateur> selectionner(Lister choix) throws EncherException{
		return utilisateurDAO.selectionner(choix);
	}
	
	public Utilisateur login(String email, String password) throws EncherException{
		EncherException encherException = new EncherException();
		Utilisateur util = utilisateurDAO.selectionner(email, password);
		
		if(util == null) {
			encherException.ajouterErreur(CodesResultatBLL.RELGE_UTILISATEUR_CONNEXION_INVALIDE);
		}
		
		if(encherException.hasErreurs()) {
			throw encherException;
		} else {
			return util;
		}
		
	}
	
	public Utilisateur selection(String pseudo) throws EncherException{
		return utilisateurDAO.selectionner(pseudo);
	}
	
	public void modfier(Utilisateur utilisateur) throws EncherException{
		
		EncherException encherException = new EncherException();
		
		validerContenu(utilisateur, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		}
		else {
			utilisateurDAO.modifier(utilisateur);
		}
	}
	
	public void ajouter(Utilisateur utilisateur) throws EncherException{
		
		EncherException encherException = new EncherException();
		
		validerContenu(utilisateur, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		}
		else {
			utilisateurDAO.ajouter(utilisateur);
		}
	}
	
	public void supprimer(Utilisateur utilisateur) throws EncherException{
		utilisateurDAO.supprimer(utilisateur);
	}
	
	private void validerContenu(Utilisateur utilisateur, EncherException encherException) throws EncherException{
		
		/*if (utilisateur.getPhotoProfil() == null || utilisateur.getPhotoProfil().isEmpty()) {
		    utilisateur.setPhotoProfil("");
		}

		if (utilisateur.getDateCreation() == null) {
		    utilisateur.setDateCreation(new Date()); // Ou toute autre valeur par défaut appropriée
		}*/
		
	    if (utilisateur.getPseudo() == null || utilisateur.getPseudo().isEmpty()) {
	        encherException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_PSEUDO_INVALIDE);
	    }

	    if (utilisateur.getPrenom() == null || utilisateur.getPrenom().isEmpty()) {
	        encherException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_PRENOM_INVALIDE);
	    }

	    if (utilisateur.getNom() == null || utilisateur.getNom().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_NOM_INVALIDE);
	    }

	    String telephone;
	    if (utilisateur.getNumeroTel() >= 100000000 && utilisateur.getNumeroTel() <= 999999999) {
	        telephone = String.format("%010d", utilisateur.getNumeroTel());
	    } else {
	        telephone = Long.toString(utilisateur.getNumeroTel());
	    }
	    if (utilisateur.getNumeroTel() == 0L || (telephone.length() != 10 || !telephone.startsWith("0") || !telephone.matches("^\\d{10}$"))) {
	        encherException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_TEL_INVALIDE);
	    }
	    
	    String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
	    if (utilisateur.getEmail() == null || utilisateur.getEmail().isEmpty() || !utilisateur.getEmail().matches(emailRegex)) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_EMAIL_INVALIDE);
	    }
	    
	    if (utilisateur.getRue() == null || utilisateur.getRue().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_RUE_INVALIDE);
	    }

	    if (utilisateur.getCodePostal() == null || utilisateur.getCodePostal().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_CP_INVALIDE);
	    }

	    if (utilisateur.getVille() == null || utilisateur.getVille().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_VILLE_INVALIDE);
	    }

	    if (utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_MDP_INVALIDE);
	    }

	    if (utilisateur.getCredit() < 0) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_CREDIT_INVALIDE);
	    }

	    if (utilisateur.getDateNaissance() == null) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_DATENAISSANCE_INVALIDE);
	    }
	    
	    
	}
}
