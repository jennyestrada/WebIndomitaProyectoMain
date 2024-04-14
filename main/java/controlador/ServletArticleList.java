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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		/*
		 * @Metodo para recuperar el parametro que le indico
		 */
		
		
			
		
			try {
				ArrayList<ArticleEditor> articleList = new ArticleEditorDao().ArticleList();
				String responseJson = new Utils().convertToJson(articleList);
				PrintWriter printWriterResponse = response.getWriter();
				printWriterResponse.print(responseJson);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
		
		
		//String responseJson="";
		
		
		//responseJson = new ArticleEditorDao().articleListJson();
		
		
		// una vez lo tenemos en formato json llamamos al objeto PrinWriter para 
		// que se vea en la pantalla del usuario.
		// con el objeto response llamamo a su metodo getWiter
		
		//PrintWriter printWriterResponse = response.getWriter();
		//printWriterResponse.print(responseJson);
		
		}
		
		
		
		/* NO LO BORRO POR SI ACASO
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//creo un objeto apartir del dao de article editor
		try {
			
			//creo un objeto dde tipo prinwriter llamado out y que me mande del response o salida 
			
			PrintWriter out = response.getWriter();
			ArticleEditorDao articleditordao = new ArticleEditorDao();
			String resultado = articleditordao.articleListJson();
			
			out.print(resultado);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// con este servlet me conecto a la BBDD recojo los datos y los muestro mediante json.
		
		
	
	
	*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		
		try {
			ArticleEditorDao articleEditorDao = new ArticleEditorDao();
			ArrayList<ArticleEditor> articleList = articleEditorDao.ArticleList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
