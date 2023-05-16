package fr.eni.EnCher.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.EnCher.bo.Categorie;
import fr.eni.EnCher.dal.DAO;
import fr.eni.EnCher.dal.JdbcTools;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.exception.EncherException;

public class CategorieDAOSqlServer implements DAO<Categorie>{
	private final String LISTER = "SELECT * FROM CATEGORIES";
	private final String AJOUTER = "INSERT INTO CATEGORIES([libelle]) VALUES (?)";
	private final String MODIFIER = "UPDATE CATEGORIES SET [libelle]=? WHERE idCategorie=?";
	private final String SUPPRIMER = "DELETE FROM CATEGORIES WHERE idCategorie=?";

	@Override
	public List<Categorie> selectionner(Lister choix) throws EncherException {
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		Categorie categorie = null;
		
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(LISTER)){
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				categorie = new Categorie(
						rs.getInt("idCategorie"),
						rs.getString("libelle"));
						listeCategorie.add(categorie);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeCategorie;
	}

	@Override
	public void ajouter(Categorie t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(AJOUTER)){
			pStmt.setString(1, t.getLibelle());
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				t.setIdCategorie(rs.getInt(1));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modifier(Categorie t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(MODIFIER)){
			pStmt.setString(1, t.getLibelle());
			pStmt.setInt(2, t.getIdCategorie());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void supprimer(Categorie t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(SUPPRIMER)){
			pStmt.setInt(1, t.getIdCategorie());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
