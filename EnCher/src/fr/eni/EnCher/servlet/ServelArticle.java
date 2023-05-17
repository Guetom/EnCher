package fr.eni.EnCher.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ServelArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServelArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtre = null;
		// Page d'acceuil (lister tout les articles
		if(request.getServletPath().equals("/")) {
			if(request.getParameter("filtre")!=null)
			{
				filtre = request.getParameterValues("filtre")[0];
			}
			switch (filtre) {
			case "cat:Voiture":
				//Filtre a ajouter
				break;

			default:
				//Tout
				
				break;
			}
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
