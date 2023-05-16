package fr.eni.EnCher.bll;

import java.util.List;

import fr.eni.EnCher.bo.Article;
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
		articleDAO.modifier(article);
	}
	
	public void ajouter(Article article) throws EncherException{
		articleDAO.ajouter(article);
	}
	
	public void supprimer(Article article) throws EncherException{
		articleDAO.supprimer(article);
	}
	

}
