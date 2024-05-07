package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.ArticleEditor;
import modelo.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ArticleEditorDao;

/**
 * Servlet implementation class ServletArticle
 */
@MultipartConfig
public class ServletArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	// creo obejto uploads de tipo File y le doy como parametro pathFiles que es el que tiene la ruta guradada
       
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

		/*
		 * @Metodo creo objeto "article" y llamo al Dao y el metodo que 
		 * devuelve el articulo completo "articleById(id)", creo otro objeto
		 * "responseJson" y llamo al metodo que convierte a JSON "convertToJson" 
		 * le paso el objeto "article" como parametro.
		 * usamos el PrintWriter para mostrar el objeto que tiene la info
		 * en formato JSON.La clase de java PrintWriter imprime archivos 
		 * de texto formateados.
		 * 
		 */

			int id = Integer.parseInt(request.getParameter("id"));
			
			
			try {
				ArticleEditor article = new ArticleEditorDao().articleById(id);
				String responseJson = new Utils().convertToJson(article);
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
		
		int op = Integer.parseInt(request.getParameter("op"));
		
		if (op == 1 || op == 2) {
			
			/*
			 * @Metodo request solo lo lee como string por ello
			// las imagenes las pasamos a string, en mi caso este
			// conversion la hace un metodo de la clase Utils. 
			 * OJO importante usamos enctype="multipart/form-data 
			 * asi que en el servlet ponemos @MultipartConfig
			 * para que lea el part.
			 */
			
			
			
			String title = request.getParameter("title");
			String text = request.getParameter("text");
			Part part = request.getPart("image");
			String fileName = Utils.getImage(part);
			String excerpt = request.getParameter("excerpt");
			System.out.println(title + text + fileName + excerpt );

		  
		    
			if (op == 1) {
				
				ArticleEditor articleEditor = new ArticleEditor (title,text,fileName,excerpt);
				try {
					articleEditor.insert();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(op == 2) {
				int id = Integer.parseInt(request.getParameter("id"));
				ArticleEditor articleEditor = new ArticleEditor(id,title,text,fileName,excerpt);
			
				try {
					articleEditor.update();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				
			}
			
		}else if (op == 3) {
			int id = Integer.parseInt(request.getParameter("id"));
			ArticleEditor articleEditor = new ArticleEditor(id);
			try {
				articleEditor.delete();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		/*
		 * @param creo variable op y guardo el parametro extraido de la URL
		 * en javaScript, es necesario parsearlo
		 */
		
		
		
		
		
			
		
}
}