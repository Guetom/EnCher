package fr.eni.EnCher.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.taglibs.standard.lang.jstl.BooleanLiteral;

import fr.eni.EnCher.bll.ArticleManager;
import fr.eni.EnCher.bll.CategorieManager;
import fr.eni.EnCher.bll.EnchereManager;
import fr.eni.EnCher.bll.PhotoManager;
import fr.eni.EnCher.bll.RetraitManager;
import fr.eni.EnCher.bo.Article;
import fr.eni.EnCher.bo.Categorie;
import fr.eni.EnCher.bo.Enchere;
import fr.eni.EnCher.bo.Photo;
import fr.eni.EnCher.bo.Retrait;
import fr.eni.EnCher.bo.Utilisateur;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.exception.EncherException;

/**
 * Servlet implementation class ServelArticle
 */
@WebServlet(
		urlPatterns= {
						"",
						"/article",
						"/article/ajouter",
						"/article/encherir"
		})
@MultipartConfig
public class ServletArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public static final String UPLOAD_DIRECTORY = "//16se19-6rh3p02/encher_images";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleManager articleManager = new ArticleManager();
		CategorieManager categorieManager = new CategorieManager();
		PhotoManager photoManager = new PhotoManager();
		EnchereManager enchereManager = new EnchereManager();
		String filtre = null;
		List<Article> listeArticle;
		List<Categorie> listeCategorie;
		// Page d'acceuil (lister tout les articles
		if(request.getServletPath() == null || request.getServletPath().equals("") || request.getServletPath().equals("/")) {
			//Tout
			try {
				listeArticle = ArticleManager.getManager().selectionner(new boolean[]{true,true,false,false}, 0, 0, "");
				listeCategorie = CategorieManager.getManager().selectionner(Lister.TOUT);
				request.setAttribute("listeArticles", listeArticle);
				request.setAttribute("categories", listeCategorie);
			} catch (EncherException e) {
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);
//			doPost(request, response);			
			
		}
		// Page détail d'un article
		else if (request.getServletPath().equals("/article")) {
			int idArticle = 0;
			idArticle = Integer.parseInt(request.getParameter("id"));
			Article article = null;
			Enchere enchere = null;
			try {
				article = articleManager.selectionner(idArticle);
				article.setListeImage(photoManager.selectionner(idArticle));
				enchere = enchereManager.selectionner(idArticle);
				if (enchere != null) {
					enchere.setArticle(article);
				}
				
				request.setAttribute("article", article);
				request.setAttribute("enchere", enchere);
			} catch (EncherException e) {
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/article/detail.jsp");
			rd.forward(request, response);
			
			
		}
		// Ajouter un article
		else if (request.getServletPath().equals("/article/ajouter")) {
			try {
				request.setAttribute("categories", CategorieManager.getManager().selectionner(Lister.TOUT));
			} catch (EncherException e) {
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/article/formArticle.jsp");
			rd.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArticleManager articleManager = new ArticleManager();
		CategorieManager categorieManager = new CategorieManager();
		PhotoManager photoManager = new PhotoManager();
		EnchereManager enchereManager = new EnchereManager();
		RetraitManager retraitManager = new RetraitManager();
		request.getSession().removeAttribute("listeArticles");
		if (request.getServletPath() == null || request.getServletPath().equals("") || request.getServletPath().equals("/")) {
			Utilisateur user = (Utilisateur) session.getAttribute("user");
			int idUtilisateur = (user == null)? 0 : user.getIdUtilisateur();
			int idCategorie = (request.getParameter("categorie") == null)? 0 : Integer.parseInt(request.getParameter("categorie"));
			String recherche = (request.getParameter("recherche") == null)? "" : request.getParameter("recherche");
			boolean radio1 = request.getParameter("radio1") != null;
			boolean radio2 = request.getParameter("radio2") != null;
			boolean check1 = request.getParameter("check1") != null;
			boolean check2 = request.getParameter("check2") != null;
			boolean check3 = request.getParameter("check3") != null;
			boolean check4 = request.getParameter("check4") != null;
			boolean check5 = request.getParameter("check5") != null;
			boolean check6 = request.getParameter("check6") != null;
			request.setAttribute("recherche", recherche);
			
			List<Article> listeArticle;
			List<Categorie> listeCategorie;
			boolean filtre[];
			if (user != null) {
				filtre = (radio1)? new boolean[]{true,check1,check2,check3} : new boolean[]{false,check4,check5,check6};
			}else {
				filtre = new boolean[]{true,true,false,false};
			}
						
			try {
				listeArticle = ArticleManager.getManager().selectionner(filtre, idUtilisateur, idCategorie, recherche);
				listeCategorie = CategorieManager.getManager().selectionner(Lister.TOUT);
				request.setAttribute("listeArticles", listeArticle);
				request.setAttribute("categories", listeCategorie);
			} catch (EncherException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);
			
			
			
		}else if (request.getServletPath().equals("/article/ajouter")) {
						
			if ((request.getParameter("nom") != null || request.getParameter("nom").equals(""))
					|| (request.getParameter("description") != null || request.getParameter("description").equals(""))
					|| (request.getParameter("categorie") != null || request.getParameter("categorie").equals(""))
					|| (request.getParameter("prix") != null || request.getParameter("prix").equals(""))
					|| (request.getParameter("dateDebut") != null || request.getParameter("dateDebut").equals(""))
					|| (request.getParameter("rue") != null || request.getParameter("rue").equals(""))
					|| (request.getParameter("codePostal") != null || request.getParameter("codePostal").equals(""))
					|| (request.getParameter("ville") != null || request.getParameter("ville").equals(""))) {
				String nom = request.getParameter("nom");
				String description = request.getParameter("description");
				int Idcategorie = Integer.parseInt(request.getParameter("categorie"));
				int prix = Integer.parseInt(request.getParameter("prix"));
				LocalDateTime dateDebut = LocalDateTime.parse(request.getParameter("dateDebut"));
				LocalDateTime dateFin = LocalDateTime.parse(request.getParameter("dateFin"));
				String rue = request.getParameter("rue");
				String codePostal = request.getParameter("codePostal");
				String ville = request.getParameter("ville");
				
				Categorie categorie = new Categorie(Idcategorie, "");
				Retrait retrait = new Retrait(rue, ville, codePostal);
				Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
				List<Photo> listePhoto = new ArrayList<Photo>();
				
				File uploadDir = new File(UPLOAD_DIRECTORY);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdirs();
		        }

		        for (Part part : request.getParts()) {
		            String fileName = getFileName(part);
		            if (fileName != null && !fileName.isEmpty()) {
		            	int indexDernierPoint = fileName.lastIndexOf(".");
		            	String newFileName = UUID.randomUUID().toString() + "." + fileName.substring(indexDernierPoint + 1);
		                String filePath = UPLOAD_DIRECTORY + File.separator + newFileName;
		                try (InputStream inputStream = part.getInputStream()) {
		                    Files.copy(inputStream, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
		                    Photo photo = new Photo(newFileName);
		                    listePhoto.add(photo);
		                }
		            }
		        }
				
				
				Article article = new Article(nom, 
						description, 
						prix, 
						dateDebut, 
						dateFin, 
						utilisateur, 
						retrait, 
						categorie, 
						null);
				
				try {
					retraitManager.ajouter(retrait);
					article.setListeImage(listePhoto);
					articleManager.ajouter(article);
					request.getSession().setAttribute("messageSucces", "L'article a bien été créé.");
					response.sendRedirect(request.getContextPath() + "/article?id=" + article.getIdArticle());
				} catch (EncherException e) {
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					e.printStackTrace();
				}
			}
		}else if (request.getServletPath().equals("/article/encherir")) {
			
			if ((request.getParameter("proposition") != null || request.getParameter("proposition").equals(""))
					|| (request.getParameter("idArticle") != null || request.getParameter("idArticle").equals(""))) {
				int proposition = Integer.parseInt(request.getParameter("proposition"));
				int idArticle = Integer.parseInt(request.getParameter("idArticle"));
				Utilisateur user = (Utilisateur) session.getAttribute("user");
				Article artTemp = new Article(idArticle);
				Enchere enchere = new Enchere(LocalDateTime.now(), proposition, user, artTemp);
				try {
					enchereManager.ajouter(enchere);
					response.sendRedirect(request.getContextPath() + "/article?id=" + idArticle);
				} catch (EncherException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
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
