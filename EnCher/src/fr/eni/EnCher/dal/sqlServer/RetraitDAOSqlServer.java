package fr.eni.EnCher.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.EnCher.bo.Categorie;
import fr.eni.EnCher.bo.Retrait;
import fr.eni.EnCher.dal.DAO;
import fr.eni.EnCher.dal.JdbcTools;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.exception.EncherException;

public class RetraitDAOSqlServer implements DAO<Retrait>{
	private final String LISTER = "SELECT * FROM RETRAITS";
	private final String AJOUTER = "INSERT INTO RETRAITS(rue, code_postal, ville) VALUES (?,?,?)";
	private final String MODIFIER = "UPDATE RETRAITS SET rue=?, code_postal=?, ville=? WHERE idRetrait=?";
	private final String SUPPRIMER = "DELETE FROM RETRAITS WHERE idRetrait=?";

	@Override
	public List<Retrait> selectionner(Lister choix) throws EncherException {
//		List<Retrait> listeRetrait = new ArrayList<Retrait>();
//		Retrait retrait = null;
//		
//		try(Connection con = JdbcTools.getConnection(); 
//				PreparedStatement pStmt = con.prepareStatement(LISTER)){
//			ResultSet rs = pStmt.executeQuery();
//			
//			while (rs.next()) {
//				retrait = new Retrait(
//						rs.getInt("idRetrait"),
//						rs.getInt("idArticle"),
//						rs.getString("rue"),
//						rs.getString("code_postal"),
//						rs.getString("ville"));
//				listeRetrait.add(retrait);
//			}			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public void ajouter(Retrait t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(AJOUTER)){
			pStmt.setString(1, t.getRue());
			pStmt.setString(2, t.getCodePostal());
			pStmt.setString(3, t.getVille());
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				t.setIdRetrait(rs.getInt(1));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modifier(Retrait t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(MODIFIER)){
			pStmt.setString(1, t.getRue());
			pStmt.setString(2, t.getCodePostal());
			pStmt.setString(3, t.getVille());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void supprimer(Retrait t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(SUPPRIMER)){
			pStmt.setInt(1, t.getIdRetrait());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
