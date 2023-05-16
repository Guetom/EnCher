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
	
	public void modfier(Utilisateur utilisateur) throws EncherException{
		utilisateurDAO.modifier(utilisateur);
	}
	
	public void ajouter(Utilisateur utilisateur) throws EncherException{
		utilisateurDAO.ajouter(utilisateur);
	}
	
	public void supprimer(Utilisateur utilisateur) throws EncherException{
		utilisateurDAO.supprimer(utilisateur);
	}

}
