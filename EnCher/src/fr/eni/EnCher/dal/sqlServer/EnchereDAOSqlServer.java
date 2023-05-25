package fr.eni.EnCher.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import fr.eni.EnCher.bo.Enchere;
import fr.eni.EnCher.bo.Photo;
import fr.eni.EnCher.bo.Utilisateur;
import fr.eni.EnCher.dal.DAO;
import fr.eni.EnCher.dal.JdbcTools;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.exception.EncherException;

public class EnchereDAOSqlServer implements DAO<Enchere>{
	private final String LISTER = "SELECT * FROM ENCHERES JOIN UTILISATEURS ON ENCHERES.idUtilisateur = UTILISATEURS.idUtilisateur JOIN ARTICLES ON ENCHERES.idArticle = ARTICLES.idArticle JOIN CATEGORIES ON ARTICLES.idCategorie = CATEGORIES.idCategorie";
	private final String LISTER_ID_ARTICLE = "SELECT * FROM ENCHERES JOIN UTILISATEURS ON ENCHERES.idUtilisateur = UTILISATEURS.idUtilisateur JOIN PHOTOS ON UTILISATEURS.idPhoto = PHOTOS.idPhoto WHERE ENCHERES.idArticle = ? AND montant = ( SELECT MAX(montant) FROM ENCHERES WHERE ENCHERES.idArticle = ?)";
	private final String AJOUTER = "INSERT INTO ENCHERES(dateheure, montant, idUtilisateur, idArticle) VALUES (?,?,?,?)";
	private final String MODIFIER = "UPDATE ENCHERES SET dateheure=?, montant=?, idUtilisateur=?, idArticle=? WHERE idEnchere=?";
	private final String SUPPRIMER = "DELETE FROM ENCHERES WHERE idEnchere=?";

	@Override
	public List<Enchere> selectionner(Lister choix) throws EncherException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Enchere selectionner(int idArticle) throws EncherException {
		Enchere enchere = null;
		
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(LISTER_ID_ARTICLE)){
			pStmt.setInt(1, idArticle);
			pStmt.setInt(2, idArticle);
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
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
				
				//Obligé de faire ça -> créer un article ça mettrait trois plombes (faudrait faire une requête du futur pour rien) + on a déjà une requête toute belle toute faite pour ça
				ArticleDAOSqlServer articleDAO = new ArticleDAOSqlServer();
				
				enchere = new Enchere(
						rs.getInt("idEnchere"),
						rs.getTimestamp("dateheure").toLocalDateTime(),
						rs.getInt("montant"),
						utilisateur,
						articleDAO.selectionner(idArticle));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return enchere;
	}

	@Override
	public void ajouter(Enchere t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(AJOUTER, Statement.RETURN_GENERATED_KEYS)){
			pStmt.setTimestamp(1, Timestamp.valueOf(t.getDateHeureEnchere()));
			pStmt.setInt(2, t.getMontant());
			pStmt.setInt(3, t.getEncherisseur().getIdUtilisateur());
			pStmt.setInt(4, t.getArticle().getIdArticle());
			pStmt.execute();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				t.setIdEnchere(rs.getInt(1));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modifier(Enchere t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(MODIFIER)){
			pStmt.setTimestamp(1, Timestamp.valueOf(t.getDateHeureEnchere()));
			pStmt.setInt(2, t.getMontant());
			pStmt.setInt(3, t.getEncherisseur().getIdUtilisateur());
			pStmt.setInt(4, t.getArticle().getIdArticle());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void supprimer(Enchere t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(SUPPRIMER)){
			pStmt.setInt(1, t.getIdEnchere());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
