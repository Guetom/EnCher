package fr.eni.EnCher.bll;

import java.util.List;

import fr.eni.EnCher.bo.Utilisateur;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.dal.sqlServer.UtilisateurDAODqlServer;
import fr.eni.EnCher.exception.EncherException;
import fr.eni.prisedenotes.BusinessException;
import fr.eni.prisedenotes.bll.CodesResultatBLL;

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
	
	public void modfier(Utilisateur utilisateur) throws EncherException{
		utilisateurDAO.modifier(utilisateur);
	}
	
	public void ajouter(Utilisateur utilisateur) throws EncherException{
		utilisateurDAO.ajouter(utilisateur);
	}
	
	public void supprimer(Utilisateur utilisateur) throws EncherException{
		utilisateurDAO.supprimer(utilisateur);
	}
	
	private void validerContenu(Utilisateur utilisateur) throws EncherException{
		
		if (utilisateur.getPhotoProfil() == null) {
			utilisateur.setP);
	    }
		
	    if (utilisateur.getPseudo() == null || utilisateur.getPseudo().isEmpty()) {
	        throw new EncherException(CodesResultatBLL.REGLE_UTILISATEUR_PSEUDO_INVALID);//throw new EncherException("Le pseudo de l'utilisateur est requis.");
	    }

	    if (utilisateur.getPrenom() == null || utilisateur.getPrenom().isEmpty()) {
	        throw new EncherException(CodesResultatBLL.REGLE_UTILISATEUR_PRENOM_INVALID);//throw new EncherException("Le prénom de l'utilisateur est requis.");
	    }

	    if (utilisateur.getNom() == null || utilisateur.getNom().isEmpty()) {
	    	throw new EncherException(CodesResultatBLL.REGLE_UTILISATEUR_NOM_INVALID);//throw new EncherException("Le nom de l'utilisateur est requis.");
	    }

	    if (utilisateur.getPhotoProfil() != null && utilisateur.getNumeroTel() <= 0) {
	    	throw new EncherException(CodesResultatBLL.);//throw new EncherException("Le numéro de téléphone de l'utilisateur est requis et doit être supérieur à zéro.");
	    }

	    if (utilisateur.getEmail() == null || utilisateur.getEmail().isEmpty()) {
	    	throw new EncherException(CodesResultatBLL.);//throw new EncherException("L'email de l'utilisateur est requis.");
	    }
	    
	    String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
	    if (!utilisateur.getEmail().matches(emailRegex)) {
	    	throw new EncherException(CodesResultatBLL.);//throw new EncherException("L'email de l'utilisateur n'est pas au format valide.");
	    }
	    
	    if (utilisateur.getRue() == null || utilisateur.getRue().isEmpty()) {
	    	throw new EncherException(CodesResultatBLL.);//throw new EncherException("La rue de l'utilisateur est requise.");
	    }

	    if (utilisateur.getCodePostal() == null || utilisateur.getCodePostal().isEmpty()) {
	    	throw new EncherException(CodesResultatBLL.);//throw new EncherException("Le code postal de l'utilisateur est requis.");
	    }

	    if (utilisateur.getVille() == null || utilisateur.getVille().isEmpty()) {
	    	throw new EncherException(CodesResultatBLL.);//throw new EncherException("La ville de l'utilisateur est requise.");
	    }

	    if (utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().isEmpty()) {
	    	throw new EncherException(CodesResultatBLL.);//throw new EncherException("Le mot de passe de l'utilisateur est requis.");
	    }

	    if (utilisateur.getCredit() < 0) {
	    	throw new EncherException(CodesResultatBLL.);//throw new EncherException("Le crédit de l'utilisateur ne peut pas être inférieur à zéro.");
	    }

	    if (utilisateur.getDateNaissance() == null) {
	    	throw new EncherException(CodesResultatBLL.);//throw new EncherException("La date de naissance de l'utilisateur est requise.");
	    }
	}
}
