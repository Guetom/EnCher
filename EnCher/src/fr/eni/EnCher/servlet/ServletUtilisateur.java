package fr.eni.EnCher.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.eni.EnCher.bll.PhotoManager;
import fr.eni.EnCher.bll.UtilisateurManager;
import fr.eni.EnCher.bo.Photo;
import fr.eni.EnCher.bo.Utilisateur;
import fr.eni.EnCher.exception.EncherException;

/**
 * Servlet implementation class ServletUtilisateur
 */
@WebServlet(urlPatterns = { 
		"/inscription", 
		"/connexion", 
		"/deconnection", 
		"/profil", 
		"/profil/modifier", 
		"/profil/supprimer" })
@MultipartConfig
public class ServletUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public static final String UPLOAD_DIRECTORY = "//16se19-6rh3p02/encher_images";

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
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/inscription.jsp");
			rd.forward(request, response);
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
			if (pseudo != null && !pseudo.isEmpty()) {
				//Afficher le profil en parametre
				try {
					user = utilisateurManager.selection(pseudo);
					session.setAttribute("utilisateur", user);
				} catch (EncherException e) {
					// TODO Auto-generated catch block
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					e.printStackTrace();
				}				
			}else if (session.getAttribute("user") != null) {
				//Afficher le profil de l'utilisateur connecté
				Utilisateur userConnect = (Utilisateur) session.getAttribute("user");
				try {
					user = utilisateurManager.selection(userConnect.getPseudo());
					session.setAttribute("utilisateur", user);
				} catch (EncherException e) {
					// TODO Auto-generated catch block
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					e.printStackTrace();
				}
			}
			
			if (session.getAttribute("user") == null && (pseudo == null || pseudo.isEmpty())){
				response.sendRedirect(request.getContextPath() + "/");
			}else {
				request.getRequestDispatcher("/WEB-INF/utilisateur/profil.jsp").forward(request, response);
			}
		} else if (request.getServletPath().equals("/profil/modifier")) {
			request.getRequestDispatcher("/WEB-INF/utilisateur/formProfil.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		PhotoManager photoManager = new PhotoManager();
		if (request.getServletPath().equals("/inscription")) {
			HttpSession session = request.getSession();
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
				String date_naissanceStr = request.getParameter("date_naissance");
				LocalDate date_naissance = LocalDate.parse(date_naissanceStr);
				String email = request.getParameter("email");
				long tel = Long.parseLong(request.getParameter("telephone"));
				String rue = request.getParameter("rue");
				String code_postal = request.getParameter("code_postal");
				String ville = request.getParameter("ville");
				String password = request.getParameter("password");

				password = hashPassword(password);
				
				File uploadDir = new File(UPLOAD_DIRECTORY);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdirs();
		        }
		        Part part = request.getPart("photo");
		        Photo photo = null;
		        if (part != null) {
		        	String fileName = getFileName(part);
		            if (fileName != null && !fileName.isEmpty()) {
		            	int indexDernierPoint = fileName.lastIndexOf(".");
		            	String newFileName = UUID.randomUUID().toString() + "." + fileName.substring(indexDernierPoint + 1);
		                String filePath = UPLOAD_DIRECTORY + File.separator + newFileName;
		                try (InputStream inputStream = part.getInputStream()) {
		                    Files.copy(inputStream, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
		                    photo = new Photo(newFileName);
		                }
		            }
					try {
						photoManager.ajouter(photo);
					} catch (EncherException e1) {
						request.setAttribute("listeCodesErreur", e1.getListeCodesErreur());
						e1.printStackTrace();
					}
				}else {
					photo = new Photo("profil.jpg");
				}
				
				Utilisateur user = new Utilisateur(pseudo, prenom, nom, tel, email, date_naissance, rue, ville,
						code_postal, password, 0, photo);
				
				try {
					utilisateurManager.ajouter(user);
					
					request.getSession().setAttribute("messageSucces", "Votre compte a bien été créé");
					response.sendRedirect(request.getContextPath() + "/connexion");
				} catch (EncherException e) {
					// TODO Auto-generated catch block
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					request.getRequestDispatcher("/WEB-INF/utilisateur/inscription.jsp").forward(request, response);
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
				
				Utilisateur user = null;
				try {
					user = utilisateurManager.login(emailPseudo, motDePasse);
				} catch (EncherException e) {
					// TODO Auto-generated catch block
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					request.getRequestDispatcher("/WEB-INF/utilisateur/connexion.jsp").forward(request, response);
					
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
			HttpSession session = request.getSession();
			if ((request.getParameter("nom") != null || request.getParameter("nom").equals(""))
					|| (request.getParameter("prenom") != null || request.getParameter("prenom").equals(""))
					|| (request.getParameter("prenom") != null || request.getParameter("prenom").equals(""))
					|| (request.getParameter("date_naissance") != null
							|| request.getParameter("date_naissance").equals(""))
					|| (request.getParameter("email") != null || request.getParameter("email").equals(""))
					|| (request.getParameter("telephone") != null || request.getParameter("telephone").equals(""))
					|| (request.getParameter("rue") != null || request.getParameter("rue").equals(""))
					|| (request.getParameter("code_postal") != null || request.getParameter("code_postal").equals(""))
					|| (request.getParameter("ville") != null || request.getParameter("ville").equals(""))) {
				
				Utilisateur userConnect = (Utilisateur) session.getAttribute("user");
				
				userConnect.setNom(request.getParameter("nom"));
				userConnect.setPrenom(request.getParameter("prenom"));
				userConnect.setDateNaissance(LocalDate.parse(request.getParameter("date_naissance")));
				userConnect.setEmail(request.getParameter("email"));
				userConnect.setRue(request.getParameter("rue"));
				userConnect.setCodePostal(request.getParameter("code_postal"));
				userConnect.setVille(request.getParameter("ville"));
				userConnect.setMotDePasse("Pa$$w0rd");
							
				File uploadDir = new File(UPLOAD_DIRECTORY);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdirs();
		        }
		        Part part = request.getPart("photo-profil");
		        Photo photo = null;
		        if (part != null) {
		        	String fileName = getFileName(part);
		            if (fileName != null && !fileName.isEmpty()) {
		            	int indexDernierPoint = fileName.lastIndexOf(".");
		            	String newFileName = UUID.randomUUID().toString() + "." + fileName.substring(indexDernierPoint + 1);
		                String filePath = UPLOAD_DIRECTORY + File.separator + newFileName;
		                try (InputStream inputStream = part.getInputStream()) {
		                    Files.copy(inputStream, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
		                    photo = new Photo(newFileName);
		                }
		            }
					try {
						photoManager.ajouter(photo);
						userConnect.setPhotoProfil(photo);
					} catch (EncherException e1) {
						request.setAttribute("listeCodesErreur", e1.getListeCodesErreur());
						e1.printStackTrace();
					}
				}
		        try {
					utilisateurManager.modfier(userConnect);
				} catch (EncherException e) {
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + "/profil");
				}
			}
			response.sendRedirect(request.getContextPath() + "/profil");
		} else if (request.getServletPath().equals("/profil/supprimer")) {
			HttpSession session = request.getSession();
			Utilisateur userConnect = (Utilisateur) session.getAttribute("user");
			
			try {
				utilisateurManager.supprimer(userConnect);
			} catch (EncherException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(session != null){
	            session.invalidate();
	        }
			response.sendRedirect(request.getContextPath() + "/");
		}else {
			response.sendRedirect(request.getContextPath() + "/");
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

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
