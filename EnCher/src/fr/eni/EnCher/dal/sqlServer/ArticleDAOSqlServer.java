package fr.eni.EnCher.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.EnCher.bo.Article;
import fr.eni.EnCher.bo.Categorie;
import fr.eni.EnCher.bo.Photo;
import fr.eni.EnCher.bo.Retrait;
import fr.eni.EnCher.bo.Utilisateur;
import fr.eni.EnCher.dal.DAO;
import fr.eni.EnCher.dal.JdbcTools;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.exception.EncherException;

public class ArticleDAOSqlServer implements DAO<Article>{
	private final String LISTER = "SELECT * FROM ARTICLES JOIN CATEGORIES ON ARTICLES.idCategorie = CATEGORIES.idCategorie JOIN UTILISATEURS ON ARTICLES.idUtilisateur = UTILISATEURS.idUtilisateur JOIN RETRAITS ON ARTICLES.idRetrait= RETRAITS.idRetrait LEFT JOIN ARTICLES_PHOTOS ON ARTICLES.idArticle = ARTICLES_PHOTOS.idArticle LEFT JOIN PHOTOS ON ARTICLES_PHOTOS.idPhoto = PHOTOS.idPhoto WHERE ARTICLES_PHOTOS.idPhoto IN (SELECT MIN(idPhoto) FROM ARTICLES_PHOTOS GROUP BY idArticle)";
	private final String LISTER_ID = "SELECT * FROM ARTICLES JOIN CATEGORIES ON ARTICLES.idCategorie = CATEGORIES.idCategorie JOIN UTILISATEURS ON ARTICLES.idUtilisateur = UTILISATEURS.idUtilisateur JOIN RETRAITS ON ARTICLES.idRetrait= RETRAITS.idRetrait LEFT JOIN ARTICLES_PHOTOS ON ARTICLES.idArticle = ARTICLES_PHOTOS.idArticle LEFT JOIN PHOTOS ON ARTICLES_PHOTOS.idPhoto = PHOTOS.idPhoto WHERE ARTICLES_PHOTOS.idPhoto IN (SELECT MIN(idPhoto) FROM ARTICLES_PHOTOS GROUP BY idArticle) AND ARTICLES.idArticle=?";
	private final String AJOUTER = "INSERT INTO ARTICLES(nom, description, prix, idCategorie, idUtilisateur, idRetrait, dateDebut, dateFin) VALUES (?,?,?,?,?,?,?,?)";
	private final String MODIFIER = "UPDATE ARTICLES SET etat=?, nom=?, description=?, prix=?, idCategorie=?, idUtilisateur=?, dateDebut=?, dateFin=? WHERE idArticle=?";
	private final String SUPPRIMER = "DELETE FROM ARTICLES WHERE idArticle=?";


	@Override
	public List<Article> selectionner(Lister choix) throws EncherException {
		List<Article> listeArticle = new ArrayList<Article>();
		Article article = null;
		
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(LISTER)){
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				
				Categorie categorie = new Categorie(
						rs.getInt("idCategorie"),
						rs.getString("libelle"));
				
				Photo photoProfil = new Photo(
						rs.getInt("idPhoto"),
						rs.getString("url"));
								
				Utilisateur utilisateur = new Utilisateur(
						rs.getInt("idUtilisateur"),
						rs.getString("pseudo"),
						rs.getString("prenom"),
						rs.getString("nom"),
						rs.getInt("tel"),
						rs.getString("email"),
						rs.getTimestamp("dateNaissance").toLocalDateTime().toLocalDate(),
						rs.getString("rue"),
						rs.getString("ville"),
						rs.getString("code_postal"),
						rs.getInt("credit"),
						rs.getTimestamp("dateCreation").toLocalDateTime(),
						photoProfil,
						rs.getBoolean("isAdmin"));
				
				Retrait retrait = new Retrait(
						rs.getInt("idRetrait"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"));
				
				Photo photo = new Photo(
						rs.getInt("idPhoto"),
						rs.getString("url"));
				
				article = new Article(
						rs.getInt("idArticle"),
						rs.getString("nom"),
						rs.getString("description"),
						rs.getInt("prix"),
						rs.getTimestamp("dateDebut").toLocalDateTime(),
						rs.getTimestamp("dateFin").toLocalDateTime(),
						utilisateur,
						retrait,
						categorie,
						rs.getString("etat"),
						photo);
				
				listeArticle.add(article);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeArticle;
	}
	
	public Article selectionner(int idArticle) throws EncherException {
		Article article = null;
		
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(LISTER_ID)){
			pStmt.setInt(1, idArticle);
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				
				Categorie categorie = new Categorie(
						rs.getInt("idCategorie"),
						rs.getString("libelle"));
				
				Photo photoProfil = new Photo(
						rs.getInt("idPhoto"),
						rs.getString("url"));
								
				Utilisateur utilisateur = new Utilisateur(
						rs.getInt("idUtilisateur"),
						rs.getString("pseudo"),
						rs.getString("prenom"),
						rs.getString("nom"),
						rs.getInt("tel"),
						rs.getString("email"),
						rs.getTimestamp("dateNaissance").toLocalDateTime().toLocalDate(),
						rs.getString("rue"),
						rs.getString("ville"),
						rs.getString("code_postal"),
						rs.getInt("credit"),
						rs.getTimestamp("dateCreation").toLocalDateTime(),
						photoProfil,
						rs.getBoolean("isAdmin"));
				
				Retrait retrait = new Retrait(
						rs.getInt("idRetrait"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"));
				
				article = new Article(
						rs.getInt("idArticle"),
						rs.getString("nom"),
						rs.getString("description"),
						rs.getInt("prix"),
						rs.getTimestamp("dateDebut").toLocalDateTime(),
						rs.getTimestamp("dateFin").toLocalDateTime(),
						utilisateur,
						retrait,
						categorie,
						rs.getString("etat"),
						null);
				
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;
	}


	@Override
	public void ajouter(Article t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(AJOUTER, Statement.RETURN_GENERATED_KEYS)){
			pStmt.setString(1, t.getNom());
			pStmt.setString(2, t.getDescription());
			pStmt.setInt(3, t.getPrix());
			pStmt.setInt(4, t.getCategorie().getIdCategorie());
			pStmt.setInt(5, t.getProprietaire().getIdUtilisateur());
			pStmt.setInt(6, t.getRetrait().getIdRetrait());
			pStmt.setTimestamp(7, java.sql.Timestamp.valueOf(t.getDateDebut()));
			pStmt.setTimestamp(8, java.sql.Timestamp.valueOf(t.getDateFin()));
			pStmt.execute();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				t.setIdArticle(rs.getInt(1));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modifier(Article t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(MODIFIER)){
			pStmt.setString(1, t.getEtat());
			pStmt.setString(2, t.getNom());
			pStmt.setString(3, t.getDescription());
			pStmt.setInt(4, t.getPrix());
			pStmt.setInt(5, t.getCategorie().getIdCategorie());
			pStmt.setInt(6, t.getProprietaire().getIdUtilisateur());
			pStmt.setInt(7, t.getRetrait().getIdRetrait());
			pStmt.setTimestamp(8, java.sql.Timestamp.valueOf(t.getDateDebut()));
			pStmt.setTimestamp(9, java.sql.Timestamp.valueOf(t.getDateFin()));
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void supprimer(Article t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(SUPPRIMER)){
			pStmt.setInt(1, t.getIdArticle());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
