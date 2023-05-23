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
	private final String AJOUTER_CORRESPONDANCE = "INSERT INTO ARTICLES_PHOTOS(idArticle, idPhoto) VALUES (?,?)";
	private final String AJOUTER_PHOTO = "INSERT INTO PHOTOS(url) VALUES (?)";
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
		if (t.getListeImage().size() >  0) {
			for (Photo photo : t.getListeImage()) {
				ajouter(photo);
				ajouterCorrespondance(t, photo);
			}
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
		if (t.getListeImage().size() >  0) {
			for (Photo photo : t.getListeImage()) {
				ajouter(photo);
				ajouterCorrespondance(t, photo);
			}
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
	

	public void ajouter(Photo t) throws EncherException {
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(AJOUTER_PHOTO, Statement.RETURN_GENERATED_KEYS)){
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
	
	private void ajouterCorrespondance(Article a, Photo p) throws EncherException{
		try(Connection con = JdbcTools.getConnection(); 
				PreparedStatement pStmt = con.prepareStatement(AJOUTER_CORRESPONDANCE)){
			pStmt.setInt(1, a.getIdArticle());
			pStmt.setInt(2, p.getIdPhoto());
			pStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String filtrage(boolean[] checkBoxes, int idUtilisateur, int idCategorie, String rechercheUtilisateur) {
	    /*
	    checkBoxes:

	    checkBoxes[0] : corespond à la position du radio button 'Achats'(si true) ou 'Mes ventes'(si false)
	    checkBoxes[1], checkBoxes[2] et checkBoxes[3] corespond aux 3 check box en dessous le radio button
	    */

	    //  /!\ Si l'utiisateur n'est pas connecter le radio button est 'disabled',
	    //  l'appel de cette fonction ce fera avec un tableau par défaut comme ceci: 

	    int nbCheckBoxesRenseignes = 0;
	    for (int i = 0; i < checkBoxes.length; i++) {
	        //La première cellule ([0]) est ignoré car contrairement aux autres elle est de toute façons rensigner. Elle corespond a un radio button (Radio button: 'Achats' -> true / 'Mes ventes' -> false)
	        if(checkBoxes[i]) nbCheckBoxesRenseignes++;
	    }
	    //Si les check box ne sont pas renseigner correctement il n'y a pas d'articles trouvés
	    if(nbCheckBoxesRenseignes == 0){
	        return null;
	    }
	    else {
	        int nbParametresRenseignes = 0;
	        if(idCategorie!=0) nbParametresRenseignes++;
	        if(rechercheUtilisateur!="") nbParametresRenseignes++;
	        if(nbCheckBoxesRenseignes>1) nbParametresRenseignes++;
	        int parametresRestants = nbParametresRenseignes;//variable servant a gérer les "AND" pour les paramètres

	        String requete = "SELECT " +
	        //colonnes d'ARTICLES
	        "ARTICLES.idArticle AS A_idArticle, ARTICLES.etat AS A_etat, ARTICLES.nom AS A_nom, ARTICLES.description AS A_description, ARTICLES.prix AS A_prix, ARTICLES.idCategorie AS A_idCategorie, ARTICLES.idUtilisateur AS A_idUtilisateur, ARTICLES.idRetrait AS A_idRetrait, ARTICLES.dateDebut AS A_dateDebut, ARTICLES.dateFin AS A_dateFin, " +
	        //colonnes d'ENCHERES
	        "ENCHERES.idEnchere AS E_idEnchere, ENCHERES.dateheure AS E_dateheure, ENCHERES.montant AS E_montant, ENCHERES.idUtilisateur AS E_idUtilisateur, ENCHERES.idArticle AS E_idArticle, " +
	        //colonnes de TAGS, valeurs concaténées
	        "STRING_AGG(TAGS.idTag, ';') AS T_idTag, STRING_AGG(TAGS.libelle, ';') AS T_libelle " +

	        //ENCHERES
	        "LEFT JOIN ( SELECT idEnchere, dateheure, montant, idUtilisateur, idArticle " +
	            "FROM ENCHERES " +
	        ") AS ENCHERES ON ARTICLES.idArticle = ENCHERES.idArticle " +
	        //ARTICLES_TAGS
	        "LEFT JOIN ( SELECT idArticle, idTag " +
		        "FROM ARTICLES_TAGS " +
	        ") AS ARTICLES_TAGS ON ARTICLES.idArticle = ARTICLES_TAGS.idArticle " +
	        //TAGS
	        "LEFT JOIN ( SELECT idTag, libelle " +
	            "FROM TAGS " +
	        ") AS TAGS ON ARTICLES_TAGS.idTag = TAGS.idTag " +

	        //Début du filtrage
	        "WHERE (";
	        
	        //'Catégorie'
	        if(idCategorie!=0){
	            requete += "ARTICLES.idCategorie =" + idCategorie;
	            parametresRestants--;
	            requete += parametresRestants!=0 ? " AND " : "";
	        }
	        //'Rechercher'
	        if(rechercheUtilisateur!=""){
	            requete += "(ARTICLES.nom LIKE '%" + rechercheUtilisateur + "%' OR TAGS.libelle LIKE '%" + rechercheUtilisateur + "%')";
	            parametresRestants--;
	            requete += parametresRestants!=0 ? " AND " : "";
	        }
	        //CREER("CR")
	        //EN_COURS("EC")
	        //VENDU("VD")
	        //RETIRER("RT")

	        //Radio button 'Achats'
	        if(checkBoxes[0]==true){
	            requete += "(";
	            if(checkBoxes[1]) requete += "ARTICLES.etat = 'EC'";//enchères ouvertes
	            if(checkBoxes[1] && checkBoxes[2]) requete += " OR ";
	            if(checkBoxes[2]) requete += "ENCHERES.idUtilisateur = " + idUtilisateur;//mes enchères
	            if((checkBoxes[1] || checkBoxes[2]) && checkBoxes[3]) requete += " OR ";
	            if(checkBoxes[3]) requete += "((ARTICLES.etat = 'VD' OR ARTICLES.etat = 'RT') AND ENCHERES.idUtilisateur = " + idUtilisateur + ")";//mes enchères remportées
	            requete += ") ";
	        }
	        //Radio button 'Mes ventes'
	        if(checkBoxes[0]==false){
	            requete += "(";
	            if(checkBoxes[1]) requete += "(ARTICLES.etat = 'EC' AND ARTICLES.idUtilisateur = " + idUtilisateur + ")";//(mes) ventes en cours
	            if(checkBoxes[1] && checkBoxes[2]) requete += " OR ";
	            if(checkBoxes[2]) requete += "(ARTICLES.etat = 'CR' AND ARTICLES.idUtilisateur = " + idUtilisateur + ")";//(mes) ventes non débutées
	            if((checkBoxes[1] || checkBoxes[2]) && checkBoxes[3]) requete += " OR ";
	            if(checkBoxes[3]) requete += "((ARTICLES.etat = 'VD' OR ARTICLES.etat = 'RT') AND ARTICLES.idUtilisateur = " + idUtilisateur + ")";//(mes) ventes terminées
	            requete += ") ";
	        }

	        //filtration ENCHERES permanente, pour avoir seulement celle avec le montant le plus haut.
	        requete+= "AND (ENCHERES.idEnchere IS NULL OR ENCHERES.montant = ( " +
	            "SELECT MAX(montant) -- Sélection du montant maximum parmi les enchères associées à l'article " +
	            "FROM ENCHERES " +
	            "WHERE ENCHERES.idArticle = ARTICLES.idArticle)) " +
	        ") GROUP BY " +
	        "ARTICLES.idArticle, ARTICLES.etat, ARTICLES.nom, ARTICLES.description, ARTICLES.prix, ARTICLES.idCategorie, ARTICLES.idUtilisateur, ARTICLES.idRetrait, ARTICLES.dateDebut, ARTICLES.dateDebut, ARTICLES.dateFin, " +
		    "ENCHERES.idEnchere, ENCHERES.dateheure, ENCHERES.montant, ENCHERES.idUtilisateur, ENCHERES.idArticle;";
	        return requete;
	    }
	}
}
