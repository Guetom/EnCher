package fr.eni.EnCher.bll;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.EnCher.bo.Article;
import fr.eni.EnCher.bo.Utilisateur;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.dal.sqlServer.ArticleDAOSqlServer;
import fr.eni.EnCher.exception.EncherException;

public class ArticleManager {
	private static ArticleManager instance;
	private ArticleDAOSqlServer articleDAO;
	
	public ArticleManager() {
		articleDAO = new ArticleDAOSqlServer();
	}
	
	public static ArticleManager getManager() {
		if (instance == null) {
			instance = new ArticleManager();			
		}
		return instance;		
	}
	
	public List<Article> selectionner(Lister choix) throws EncherException{
		return articleDAO.selectionner(choix);
	}
	
	public void modfier(Article article) throws EncherException{
		
		EncherException encherException = new EncherException();
		
		validerContenu(article, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		}
		
		articleDAO.modifier(article);
	}
	
	public void ajouter(Article article) throws EncherException{
		
		EncherException encherException = new EncherException();
		
		validerContenu(article, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		}
		
		articleDAO.ajouter(article);
	}
	
	public void supprimer(Article article) throws EncherException{
		
		EncherException encherException = new EncherException();
		
		validerContenu(article, encherException);
		
		if(encherException.hasErreurs()) {
			throw encherException;
		}
		
		articleDAO.supprimer(article);
	}
	
	private void validerContenu(Article article, EncherException encherException) throws EncherException{
	    
	    if (article.getNom() == null || article.getNom().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_NOM_INVALIDE);
	    }
	    
	    if (article.getDescription() == null || article.getDescription().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DESCRIPTION_INVALIDE);
	    }
	    
	    if (article.getPrix() < 0) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_PRIX_NEGATIF);
	    }
	    
	    if(article.getCategorie() == null) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_CATEGORIE_INVALIDE);
	    }
	    
	    if(article.getProprietaire() == null) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_UTILISATEUR_INVALIDE);
	    }
	    
	    if(article.getRetrait() == null) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_RETRAIT_INVALIDE);
	    }
	    
	    if(article.getDateDebut().isBefore(LocalDateTime.now())) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_DEBUT_DANS_LE_PASSE);
	    }
	    
	    if(article.getDateFin().isBefore(LocalDateTime.now())) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_FIN_DANS_LE_PASSE);
	    }
	    
	    if(article.getDateDebut().isAfter(article.getDateFin())) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_DEBUT_APRES_FIN);
	    }

	}
}
