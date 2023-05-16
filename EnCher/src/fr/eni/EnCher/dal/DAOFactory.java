package fr.eni.EnCher.dal;

import fr.eni.EnCher.bo.Article;
import fr.eni.EnCher.bo.Categorie;
import fr.eni.EnCher.bo.Enchere;
import fr.eni.EnCher.bo.Photo;
import fr.eni.EnCher.bo.Retrait;
import fr.eni.EnCher.bo.Tag;
import fr.eni.EnCher.bo.Utilisateur;
import fr.eni.EnCher.dal.sqlServer.ArticleDAOSqlServer;
import fr.eni.EnCher.dal.sqlServer.CategorieDAOSqlServer;
import fr.eni.EnCher.dal.sqlServer.EnchereDAOSqlServer;
import fr.eni.EnCher.dal.sqlServer.PhotoDAOSqlServer;
import fr.eni.EnCher.dal.sqlServer.RetraitDAOSqlServer;
import fr.eni.EnCher.dal.sqlServer.TagDAOSqlServer;
import fr.eni.EnCher.dal.sqlServer.UtilisateurDAODqlServer;

public class DAOFactory {
	//Utilisateur
	//Article
	//Categorie
	//Tag
	//Retrait
	//Enchere
	//Photo
	
	public static DAO<Utilisateur> getUtilisateurDAO(){
		DAO<Utilisateur> utilisateurDAO = new UtilisateurDAODqlServer();
		return utilisateurDAO;
	}
	
	public static DAO<Article> getArticleDAO(){
		DAO<Article> articleDAO = new ArticleDAOSqlServer();
		return articleDAO;
	}
	
	public static DAO<Categorie> getCategorieDAO(){
		DAO<Categorie> categorieDAO = new CategorieDAOSqlServer();
		return categorieDAO;
	}
	
	public static DAO<Tag> getTagDAO(){
		DAO<Tag> tagDAO = new TagDAOSqlServer();
		return tagDAO;
	}
	
	public static DAO<Retrait> getRetraitDAO(){
		DAO<Retrait> retraitDAO = new RetraitDAOSqlServer();
		return retraitDAO;
	}
	
	public static DAO<Enchere> getEnchereDAO(){
		DAO<Enchere> enchereDAO = new EnchereDAOSqlServer();
		return enchereDAO;
	}
	
	public static DAO<Photo> getPhotoDAO(){
		DAO<Photo> photoDAO = new PhotoDAOSqlServer();
		return photoDAO;
	}
	
}