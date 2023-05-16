package fr.eni.EnCher.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.EnCher.bo.Categorie;
import fr.eni.EnCher.bo.Photo;
import fr.eni.EnCher.bo.Utilisateur;
import fr.eni.EnCher.dal.DAO;
import fr.eni.EnCher.dal.JdbcTools;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.exception.EncherException;

public class UtilisateurDAODqlServer implements DAO<Utilisateur>{
	private final String LISTER = "SELECT * FROM UTILISATEURS INNER JOIN PHOTOS ON UTILISATEURS.idPhoto = PHOTOS.idPhoto";
	private final String LISTER_UTILISATEUR = "SELECT * FROM UTILISATEURS INNER JOIN PHOTOS ON UTILISATEURS.idPhoto = PHOTOS.idPhoto WHERE idUtilisateur=?";
	private final String AJOUTER = "INSERT INTO UTILISATEURS(pseudo, prenom, nom, idPhoto, tel, email, rue, code_postal, ville, mot_de_passe, credit, isAdmin, dateNaissance) VALUES (?,?,?,?)";
	private final String MODIFIER = "UPDATE UTILISATEURS SET idArticle=?, rue=?, code_postal=?, ville=? WHERE idRetrait=?";
	private final String SUPPRIMER = "DELETE FROM UTILISATEURS WHERE idRetrait=?";
	
	public Utilisateur selectionner(int idUtilisateur) throws EncherException {
		Utilisateur utilisateur = null;
		
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(LISTER_UTILISATEUR)){
			pStmt.setInt(1, idUtilisateur);
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				Photo photoProfil = new Photo(
						rs.getInt("idPhoto"),
						rs.getString("url"));
								
				utilisateur = new Utilisateur(
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
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateur;
	}
	
	@Override
	public List<Utilisateur> selectionner(Lister choix) throws EncherException {
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		Utilisateur utilisateur = null;
		
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(LISTER)){
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				Photo photoProfil = new Photo(
						rs.getInt("idPhoto"),
						rs.getString("url"));
								
				utilisateur = new Utilisateur(
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
				
						listeUtilisateur.add(utilisateur);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeUtilisateur;
	}

	@Override
	public void ajouter(Utilisateur t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(AJOUTER)){
			pStmt.setString(1, t.getPseudo());
			pStmt.setString(2, t.getPrenom());
			pStmt.setString(3, t.getNom());
			pStmt.setInt(4, t.getPhotoProfil().getIdPhoto());
			pStmt.setInt(5, t.getNumeroTel());
			pStmt.setString(6, t.getEmail());
			pStmt.setString(7, t.getRue());
			pStmt.setString(8, t.getCodePostal());
			pStmt.setString(9, t.getVille());
			pStmt.setString(10, t.getMotDePasse());
			pStmt.setInt(11, t.getCredit());
			pStmt.setBoolean(12, t.isAdmin());
			pStmt.setDate(13, java.sql.Date.valueOf(t.getDateNaissance()));			
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				t.setIdUtilisateur(rs.getInt(1));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modifier(Utilisateur t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(MODIFIER)){
			pStmt.setString(1, t.getPseudo());
			pStmt.setString(2, t.getPrenom());
			pStmt.setString(3, t.getNom());
			pStmt.setInt(4, t.getPhotoProfil().getIdPhoto());
			pStmt.setInt(5, t.getNumeroTel());
			pStmt.setString(6, t.getEmail());
			pStmt.setString(7, t.getRue());
			pStmt.setString(8, t.getCodePostal());
			pStmt.setString(9, t.getVille());
			pStmt.setString(10, t.getMotDePasse());
			pStmt.setInt(11, t.getCredit());
			pStmt.setBoolean(12, t.isAdmin());
			pStmt.setDate(13, java.sql.Date.valueOf(t.getDateNaissance()));	
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void supprimer(Utilisateur t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(SUPPRIMER)){
			pStmt.setInt(1, t.getIdUtilisateur());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
