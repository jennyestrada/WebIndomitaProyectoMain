package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.ArticleEditor;
import modelo.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.AccessDao;
import dao.ArticleEditorDao;

/**
 * Servlet implementation class ServletListImages
 */
public class ServletArticleList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletArticleList() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * Metodo para traer la lista de articulos desde el Dao.
	 * PrintWriter imprime la lista de articulos ya convertidos a JSON
	 * @return una lista de articulos convertidos a JSON
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	
	
		
			try {
				ArrayList<ArticleEditor> articleList = new ArticleEditorDao().ArticleList();
				String responseJson = new Utils().convertToJson(articleList);
				PrintWriter printWriterResponse = response.getWriter();
				printWriterResponse.print(responseJson);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
		
		
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		
	
		
		
		
		
		
	}

}
