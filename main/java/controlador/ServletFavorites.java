package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Access;
import modelo.ArticleEditor;
import modelo.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ArtiFavoritesDao;
import dao.ArticleEditorDao;


/**
 * Esta tiene dos metodo, uno para añadir los articulos favoritos de un usuario 
 * y otro para listar estos articulos.
 */
public class ServletFavorites extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFavorites() {
        super();
        // TODO Auto-generated constructor stub
    }
    


	/**
	 * Metodo para obtener la lista de los articulo favoritos de el usuario que esta logueado.
	 * @return devolvera una lista de articulos favoritos convertida a JSON.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//falta traer los articulos a mi lista privada
		
		
		
		session = request.getSession();
		int idUser = (int) session.getAttribute("id");
	
		
		try {
			ArrayList<ArticleEditor> articleFavoritesList = new ArtiFavoritesDao().articleFavoritesList(idUser);
			String responseJson = new Utils().convertToJson(articleFavoritesList);
			PrintWriter printWriterResponse = response.getWriter();
			printWriterResponse.print(responseJson);
			System.out.println(" prueba a ver si traer articulos "+ responseJson);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
		
	}
	
	
	
	
	
	/**
	 * Metodo para insertar los articulos favoritos de un usuario logueado.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		session = request.getSession();
		
		
		//Obtener los valores de los campos hidden del formulario
		
		
		
		int idArticle = Integer.parseInt(request.getParameter("hiddenIdArticle"));
		int idAccess = (int) session.getAttribute("id");
		
		
		try {
			ArtiFavoritesDao favoritesdao = new ArtiFavoritesDao();
			favoritesdao.insertFavorites(idAccess, idArticle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
	}

}
