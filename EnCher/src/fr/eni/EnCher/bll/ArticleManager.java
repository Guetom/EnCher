package fr.eni.EnCher.bll;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.EnCher.bo.Article;
import fr.eni.EnCher.bo.Utilisateur;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.dal.sqlServer.ArticleDAOSqlServer;
import fr.eni.EnCher.exception.EncherException;
import fr.eni.prisedenotes.BusinessException;

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
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_NOM_INVALID);//Le nom de l'article est requis.
	    }
	    
	    if (article.getDescription() == null || article.getDescription().isEmpty()) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DESCRIPTION_INVALID);//Une description de l'article est requise.
	    }
	    
	    if (article.getPrix() < 0) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_PRIX_NEGATIF);//Le prix de l'article ne peut pas être négatif.
	    }
	    
	    if(article.getCategorie() == null) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_CATEGORIE_NULL);//Une catégorie d'article est requise.
	    }
	    
	    if(article.getProprietaire() == null) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_UTILISATEUR_NULL);//Aucun utilisateur n'est lié à l'article.
	    }
	    
	    if(article.getRetrait() == null) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_RETRAIT_NULL);//L'adresse de retrait pour l'article est requise.
	    }
	    
	    if(article.getDateDebut().isBefore(LocalDateTime.now())) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_DEBUT_DANS_LE_PASSE);//Impossible de mettre la date de début d'enchères dans le passé.
	    }
	    
	    if(article.getDateFin().isBefore(LocalDateTime.now())) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_FIN_DANS_LE_PASSE);//Impossible de mettre la date de fin d'enchères dans le passé.
	    }
	    
	    if(article.getDateDebut().isAfter(article.getDateFin())) {
	    	encherException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_DEBUT_APRES_FIN);//Impossible de mettre la date de début d'enchères après la date de fin d'enchères.
	    }

	}
}
