package fr.eni.EnCher.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.EnCher.bll.ArticleManager;
import fr.eni.EnCher.dal.Lister;
import fr.eni.EnCher.exception.EncherException;

/**
 * Servlet implementation class ServelArticle
 */
@WebServlet(
		urlPatterns= {
						"/",
						"/article",
						"/article/ajouter",
						"/article/supprimer"
		})
public class ServletArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager = new ArticleManager();
		String filtre = null;
		// Page d'acceuil (lister tout les articles
		if(request.getServletPath().equals("/")) {
			//Tout
			try {
				request.setAttribute("listeArticles", articleManager.getManager().selectionner(Lister.TOUT));
			} catch (EncherException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);
//			if(request.getParameter("filtre")!=null)
//			{
//				filtre = request.getParameterValues("filtre")[0];
//			}
//			switch (filtre) {
//			case "cat:Voiture":
//				//Filtre a ajouter
//				break;
//
//			default:
//				//Tout
//				try {
//					request.setAttribute("listeArticles", articleManager.getManager().selectionner(Lister.TOUT));
//				} catch (EncherException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
//			}
		}
		// Page détail d'un article
		else if (request.getServletPath().equals("/article")) {
			
		}
		// Ajouter un article
		else if (request.getServletPath().equals("/article/ajouter")) {
			
		}
		// Supprimer un article
		else if (request.getServletPath().equals("/article/supprimer")) {
			
		}
		//		doGet(request, response);
	}

}
