package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.ArticleEditor;

import java.io.IOException;
import java.sql.SQLException;

import dao.ArtiFavoritesDao;


/**
 * Servlet implementation class ServletFavorites
 */
public class ServletFavorites extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFavorites() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		// Obtener los valores de los campos hidden del formulario
		int idArticle = Integer.parseInt(request.getParameter("hiddenIdArticle"));
		int idAccess = Integer.parseInt(request.getParameter("hiddenIdAccess"));
		
		
		try {
			ArtiFavoritesDao favoritesdao = new ArtiFavoritesDao();
			favoritesdao.insertFavorites(idAccess, idArticle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
	}

}
