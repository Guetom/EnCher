package fr.eni.EnCher.bll;

import java.util.List;

import fr.eni.EnCher.bo.Categorie;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.dal.sqlServer.CategorieDAOSqlServer;
import fr.eni.EnCher.exception.EncherException;

public class CategorieManager {
	private static CategorieManager instance;
	private CategorieDAOSqlServer categorieDAO = new CategorieDAOSqlServer();
	
	public static CategorieManager getManager() {
		if (instance == null) {
			instance = new CategorieManager();			
		}
		return instance;		
	}
	
	public List<Categorie> selectionner(Lister choix) throws EncherException{
		return categorieDAO.selectionner(choix);
	}
	
	public void modfier(Categorie categorie) throws EncherException{
		categorieDAO.modifier(categorie);
	}
	
	public void ajouter(Categorie categorie) throws EncherException{
		categorieDAO.ajouter(categorie);
	}
	
	public void supprimer(Categorie categorie) throws EncherException{
		categorieDAO.supprimer(categorie);
	}
	
	private void validerContenu(Categorie categorie, EncherException encherException) throws EncherException{
	    
	    if (categorie.getLibelle() == null || categorie.getLibelle().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIE_LIBELLE_INVALIDE);
	    }
	}

}
