package fr.eni.EnCher.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.EnCher.bo.Photo;
import fr.eni.EnCher.dal.DAO;
import fr.eni.EnCher.dal.JdbcTools;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.exception.EncherException;

public class PhotoDAOSqlServer implements DAO<Photo>{
	private final String LISTER = "SELECT * FROM PHOTOS";
	private final String LISTER_ID_ARTICLE = "SELECT * FROM PHOTOS JOIN ARTICLES_PHOTOS ON PHOTOS.idPhoto = ARTICLES_PHOTOS.idPhoto WHERE idArticle=?";
	private final String AJOUTER = "INSERT INTO PHOTOS(url) VALUES (?)";
	private final String MODIFIER = "UPDATE PHOTOS SET url=? WHERE idPhoto=?";
	private final String SUPPRIMER = "DELETE FROM PHOTOS WHERE idPhoto=?";

	@Override
	public List<Photo> selectionner(Lister choix) throws EncherException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Photo> selectionner(int idArticle) throws EncherException {
		List<Photo> listePhoto = new ArrayList<Photo>();
		
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(LISTER_ID_ARTICLE)){
			pStmt.setInt(1, idArticle);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				Photo photo = new Photo(
						rs.getInt("idPhoto"),
						rs.getString("url"));
				
				listePhoto.add(photo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listePhoto;
	}

	@Override
	public void ajouter(Photo t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(AJOUTER, Statement.RETURN_GENERATED_KEYS)){
			pStmt.setString(1, t.getUrl());
			pStmt.execute();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				t.setIdPhoto(rs.getInt(1));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modifier(Photo t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(MODIFIER)){
			pStmt.setString(1, t.getUrl());
			pStmt.setInt(2, t.getIdPhoto());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void supprimer(Photo t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(SUPPRIMER)){
			pStmt.setInt(1, t.getIdPhoto());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
