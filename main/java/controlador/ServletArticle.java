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

// 	Esto se debe poner cuando se enviar imagenes
@MultipartConfig
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
	 * Metodo para llamar al metodo de la Dao que trae toda la informacion del articulo
	 * al que corresponda el Id que se le pasa.
	 * @retun el articulo en formato JSON
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
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
	 * Metodo para insertar editar y borrar.
	 * si es op 1 llama al metodo insert de la clase.
	 * si es op 2 llama al metodo update de la clase.
	 * si es op 3 llama al metodo delete de la clase.
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	
		
		int op = Integer.parseInt(request.getParameter("op"));
		
		if (op == 1 || op == 2) {
			
			
		
		
			
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
					response.sendRedirect("articlelist.html");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(op == 2) {
				// toma el name id de campo oculto.
				int id = Integer.parseInt(request.getParameter("id"));
				ArticleEditor articleEditor = new ArticleEditor(id,title,text,fileName,excerpt);
			
				try {
					articleEditor.update();
					response.sendRedirect("articlelist.html");
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				
			}
			
		}else if (op == 3) {
			// es igual que sacar los datos de los campos title text exerpt, con el request pedimos el 
			// valor del name id y lo metemos en una variable en este caso varible int id.
			// en el caso de borrar el id es un input hidden. igualpara actualizar.
			int id = Integer.parseInt(request.getParameter("id"));
			ArticleEditor articleEditor = new ArticleEditor(id);
			try {
				articleEditor.delete();
				response.sendRedirect("articlelist.html");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		
		
			
		
}
}