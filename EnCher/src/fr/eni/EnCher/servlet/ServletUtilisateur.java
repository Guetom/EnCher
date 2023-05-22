package fr.eni.EnCher.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import java.nio.charset.StandardCharsets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.EnCher.bll.PhotoManager;
import fr.eni.EnCher.bll.UtilisateurManager;
import fr.eni.EnCher.bo.Photo;
import fr.eni.EnCher.bo.Utilisateur;
import fr.eni.EnCher.exception.EncherException;

/**
 * Servlet implementation class ServletUtilisateur
 */
@WebServlet(urlPatterns = { "/inscription", "/connexion", "/deconnection", "/profil", "/profil/modifier", "/profil/supprimer" })
public class ServletUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/inscription")) {
			request.getRequestDispatcher("/WEB-INF/utilisateur/inscription.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/connexion")) {
			request.getRequestDispatcher("/WEB-INF/utilisateur/connexion.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/deconnection")) {
			HttpSession session = request.getSession(false);
	        if(session != null){
	            session.invalidate();
	        }
	        response.sendRedirect(request.getContextPath() + "/connexion");
	        
		} else if (request.getServletPath().equals("/profil")) {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur user = null;
			HttpSession session = request.getSession(false);
			String pseudo = "";
			pseudo = request.getParameter("pseudo");
			if (!pseudo.isEmpty()) {
				//Afficher le profil en parametre
				try {
					user = utilisateurManager.selection(pseudo);
				} catch (EncherException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}else if (session.getAttribute("user") != null && pseudo.isEmpty()) {
				//Afficher le profil de l'utilisateur connecté
				Utilisateur userConnect = (Utilisateur) session.getAttribute("user");
				try {
					user = utilisateurManager.selection(userConnect.getPseudo());
				} catch (EncherException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				response.sendRedirect(request.getContextPath() + "/connexion");
			}
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/utilisateur/profil.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/profil/modifier")) {
			request.getRequestDispatcher("/WEB-INF/utilisateur/formProfil.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/profil/supprimer")) {
			request.getRequestDispatcher("/WEB-INF/utilisateur/supprimer.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/inscription")) {
			if ((request.getParameter("pseudo") != null || request.getParameter("pseudo").equals(""))
					|| (request.getParameter("nom") != null || request.getParameter("nom").equals(""))
					|| (request.getParameter("prenom") != null || request.getParameter("prenom").equals(""))
					|| (request.getParameter("date_naissance") != null
							|| request.getParameter("date_naissance").equals(""))
					|| (request.getParameter("email") != null || request.getParameter("email").equals(""))
					|| (request.getParameter("rue") != null || request.getParameter("rue").equals(""))
					|| (request.getParameter("code_postal") != null || request.getParameter("code_postal").equals(""))
					|| (request.getParameter("ville") != null || request.getParameter("ville").equals(""))
					|| (request.getParameter("password") != null || request.getParameter("password").equals(""))
					|| (request.getParameter("password_confirm") != null
							|| request.getParameter("password_confirm").equals(""))
					|| (request.getParameter("password") != request.getParameter("password_confirm"))) {

				String pseudo = request.getParameter("pseudo");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				Photo photo = new Photo(request.getParameter("photo"));
				String date_naissanceStr = request.getParameter("date_naissance");
				LocalDate date_naissance = LocalDate.parse(date_naissanceStr);
				String email = request.getParameter("email");
				long tel = Long.parseLong(request.getParameter("telephone"));
				String rue = request.getParameter("rue");
				String code_postal = request.getParameter("code_postal");
				String ville = request.getParameter("ville");
				String password = request.getParameter("password");

				password = hashPassword(password);
				
				PhotoManager photoManager = new PhotoManager();
				
				try {
					photoManager.ajouter(photo);
				} catch (EncherException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Utilisateur user = new Utilisateur(pseudo, prenom, nom, tel, email, date_naissance, rue, ville,
						code_postal, password, 0, photo);
				
				
				UtilisateurManager utilisateurManager = new UtilisateurManager();
				
				try {
					utilisateurManager.ajouter(user);
					response.sendRedirect(request.getContextPath() + "/connexion");
				} catch (EncherException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				// Erreur a ajouter
				request.getRequestDispatcher("/WEB-INF/utilisateur/inscription.jsp").forward(request, response);

			}
		} else if (request.getServletPath().equals("/connexion")) {
			if ((request.getParameter("emailPseudo") != null || request.getParameter("emailPseudo").equals(""))
					|| (request.getParameter("password") != null || request.getParameter("password").equals(""))) {
				String emailPseudo = request.getParameter("emailPseudo");
				String motDePasse = hashPassword(request.getParameter("password"));
				
				UtilisateurManager utilisateurManager = new UtilisateurManager();
				Utilisateur user = null;
				try {
					user = utilisateurManager.login(emailPseudo, motDePasse);
				} catch (EncherException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Utilisateur avec le bon login et mdp donc trouvé
				if (user != null) {
					//Vérification si il y a une ancienne session
					HttpSession oldSession = request.getSession(false);
		            if (oldSession != null) {
		                oldSession.invalidate();
		            }
		            HttpSession newSession = request.getSession(true);
		            newSession.setMaxInactiveInterval(5*60); //durée de vie de la session 5 * 60 sec = 5 minutes
		            
		            newSession.setAttribute("user", user);
		            
		            response.sendRedirect(request.getContextPath() + "/");
				}
				
			} else {
				// Erreur a ajouter
				request.getRequestDispatcher(request.getContextPath() + "/connexion");

			}
		} else if (request.getServletPath().equals("/profil")) {
			request.getRequestDispatcher("/WEB-INF/utilisateur/profil.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/profil/modifier")) {
			request.getRequestDispatcher("/WEB-INF/utilisateur/modifier.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/profil/supprimer")) {
			request.getRequestDispatcher("/WEB-INF/utilisateur/supprimer.jsp").forward(request, response);
		}
	}

	private String hashPassword(String password) {
		try {
			return toHexString(getSHA(password.trim()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}

	private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
		/* MessageDigest instance for hashing using SHA256 */
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		/*
		 * digest() method called to calculate message digest of an input and return
		 * array of byte
		 */
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}

	private static String toHexString(byte[] hash) {
		/* Convert byte array of hash into digest */
		BigInteger number = new BigInteger(1, hash);

		/* Convert the digest into hex value */
		StringBuilder hexString = new StringBuilder(number.toString(16));

		/* Pad with leading zeros */
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}

}
